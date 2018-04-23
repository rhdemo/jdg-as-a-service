#!/usr/bin/env bash

git clone git@github.com:rhdemo/secretstuff.git || true
if [ -d "secretstuff" ]; then
  cp secretstuff/jdg/clouds ./group_vars/clouds
fi

ansible-playbook -i hosts refresh.yml $*