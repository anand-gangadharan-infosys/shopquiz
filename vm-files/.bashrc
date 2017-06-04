aws_staging() {
  export AWS_PROFILE=staging
  export AWS_REGION=eu-west-1
  export AWS_DEFAULT_REGION=eu-west-1
}

aws_production() {
  export AWS_PROFILE=production
  export AWS_REGION=us-east-1
  export AWS_DEFAULT_REGION=us-east-1
}

aws_ci() {
  export AWS_PROFILE=ci
  export AWS_REGION=eu-west-1
  export AWS_DEFAULT_REGION=eu-west-1
}

alias ls='ls -G'
alias vi=vim
alias du='du -h'
alias dutop='\du -a . | sort -n -r | head -n 10'
