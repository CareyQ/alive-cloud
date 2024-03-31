 find . -name 'application-dev.yml' -exec git update-index --assume-unchanged {} \;
 find . -name 'bootstrap-dev.yml' -exec git update-index --assume-unchanged {} \;