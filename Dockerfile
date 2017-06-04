#
# Ubuntu Dockerfile
#
# https://github.com/dockerfile/ubuntu
#

# Pull base image.
FROM maven:3.5.0-jdk-7

# Install.
RUN \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get -qq update && \
  apt-get install -y git htop man unzip vim wget && \
  rm -rf /var/lib/apt/lists/* && \
  wget https://github.com/anand-gangadharan-infosys/shopquiz/archive/master.zip && \
  unzip master.zip && \
  cd shopquiz-master/quick-simulate && \
  mvn install 


# Add files.
ADD vm-files/.bashrc /root/.bashrc
ADD vm-files/.bash_login /root/.bash_login

# Set environment variables.
ENV HOME /root

# Define working directory.
WORKDIR /root
# Install app server

# Define default command.
CMD ["bash"]
