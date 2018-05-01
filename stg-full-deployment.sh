oc cluster up || true
token=`oc whoami -t`
ansible-playbook -i hosts full-deployment.yml --extra-vars "stage_jdg_project=datagrid-stg aws_jdg_project=datagrid-stg azure_jdg_project=datagrid-stg" $*