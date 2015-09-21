export PATH=/opt/utils/bin:$PATH
export PUBLIC_IP=$(findLinuxIP.sh eth1)
export BRIDGE_IP=$(findLinuxIP.sh docker0)
