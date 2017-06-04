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
  rm -rf /var/lib/apt/lists/*


# Add files.
ADD vm-files/.bashrc /root/.bashrc
ADD vm-files/.bash_login /root/.bash_login
ADD vm-files/build.sh /root/build.sh
ADD vm-files/run.sh /root/run.sh

RUN chmod +x /root/build.sh
RUN chmod +x /root/run.sh

RUN /root/build.sh

# Set environment variables.
ENV HOME /root

# Define working directory.
WORKDIR /root

# Define default command.
CMD ["bash"]
