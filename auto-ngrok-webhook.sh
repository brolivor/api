#!/bin/sh

# make sure that ngrok binary is located at this path on jenkins server: /opt/ngrok
/opt/ngrok/ngrok http jenkins:8080 --log=stdout > /opt/ngrok/ngrok.log &