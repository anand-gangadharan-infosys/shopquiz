package com.anand.shopquiz.quick_simulate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import com.anand.shopquiz.quick_simulate.actors.Cashier;
import com.anand.shopquiz.quick_simulate.actors.Customer;
import com.anand.shopquiz.quick_simulate.actors.ExpertCashier;
import com.anand.shopquiz.quick_simulate.actors.TraineeCashier;
import com.anand.shopquiz.quick_simulate.entities.Shop;
import com.anand.shopquiz.quick_simulate.exceptions.InvalidConfigFileException;
import com.anand.shopquiz.quick_simulate.utils.CustomerArrivalComparator;

public class Cli {
	private static final Logger log = Logger.getLogger(Cli.class.getName());
	private String[] args = null;
	private Options options = new Options();

	List<Cashier> cashiers = new ArrayList<Cashier>();
	List<Customer> customers = new ArrayList<Customer>();
	Queue<Customer> sortedCustomers;

	public Cli(String[] args) {

		this.args = args;
		options.addOption("h", "help", false, "show help.");
		options.addOption("v", "verbose", true, "support levels - all trace debug info warn and error");
		options.addOption("c", "config", true,
				"Input Customer Arrival File for simulation. "
						+ "The first line should contain the number of Cashiers. "
						+ "Subsequest lines dhould be organized as <Customer Type><TAB><Arrival Time><TAB><Number of Items>."
						+ "Customer Type takes A or B and Rest are numbers");

	}

	public void parse() {
		CommandLineParser parser = new BasicParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))
				help();
			
			if (cmd.hasOption("v")){
				String logLevel = cmd.getOptionValue("v");
				LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
				Configuration config = ctx.getConfiguration();
				LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
				loggerConfig.setLevel(org.apache.logging.log4j.Level.toLevel(logLevel));
				ctx.updateLoggers();
			}
				
	
			if (cmd.hasOption("c")) {
				log.log(Level.INFO, "Simulating " + cmd.getOptionValue("c"));
				String configFile = cmd.getOptionValue("c");


				File file = new File(configFile);
				try {
					List<String> lines = FileUtils.readLines(file, "UTF-8");
					int i = 0;
					for (String line : lines) {
						if (i == 0) {
							buildCashiers(line);
						} else {
							buildCustomers(line);
						}
						i++;
					}
					Collections.sort(customers, new CustomerArrivalComparator());
					sortedCustomers = new LinkedList<Customer>(customers);

					startSimulation();

				} catch (InvalidConfigFileException e) {
					log.log(Level.SEVERE, e.getMessage());
				} catch (IOException e) {
					log.log(Level.SEVERE, "configFile");
				}
			} else {
				help();
			}

		} catch (ParseException e) {
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		}
	}

	private void startSimulation() {
		Shop shop = new Shop(cashiers, sortedCustomers);
		int i = 0;
		while (!shop.isDone()) {
			shop.processClockTick(i++);
		}
		System.out.println("Finished at t="+(i-1)+" minutes");
	}

	private void buildCustomers(String line) throws InvalidConfigFileException {
		Customer customer = new Customer(line);
		customers.add(customer);
	}

	private void buildCashiers(String noOfCashiers) throws InvalidConfigFileException {
		try {
			int noC = Integer.parseInt(noOfCashiers);
			if (noC <= 0) {
				throw new InvalidConfigFileException("Need some cashiers in first line");
			}
			for (int i = 0; i < noC; i++) {
				Cashier cashier;
				if (i == noC - 1) {
					cashier = new TraineeCashier();
				} else {
					cashier = new ExpertCashier();
				}
				cashiers.add(cashier);
			}
		} catch (NumberFormatException e) {
			throw new InvalidConfigFileException("Cashier count should be a Whole Number above 1");
		}

	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("You can run this application", options);
		System.exit(0);
	}
}