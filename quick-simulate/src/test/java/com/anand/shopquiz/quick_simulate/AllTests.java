package com.anand.shopquiz.quick_simulate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.anand.shopquiz.quick_simulate.actors.CashierTest;
import com.anand.shopquiz.quick_simulate.entities.CashierSelectTest;
import com.anand.shopquiz.quick_simulate.entities.CustomerTypeTest;
import com.anand.shopquiz.quick_simulate.entities.ShopTest;

@RunWith(Suite.class)
@SuiteClasses({ AppTest.class, CashierSelectTest.class, CashierTest.class, ShopTest.class, CustomerTypeTest.class })
public class AllTests {

}
