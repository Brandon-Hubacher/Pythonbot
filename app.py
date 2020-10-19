import os
import sys
import json

from urllib.parse import urlencode
from urllib.request import Request, urlopen

from flask import Flask, request

app = Flask(__name__)

@app.route('/', methods=['POST'])
def webhook():
  data = request.get_json()
  log('Recieved {}'.format(data))

  # We don't want to reply to ourselves!
  if data['name'] != 'Pythonbot':
    if data['text'] == "!LR":
      #get rank for corresponding ID
    elif data['text'] == "!LG":
      #get rank for corresponding ID
    elif data['text'] == "!LRrank":
      #get rank for corresponding ID
    elif data['text'] == "!LGrank":
      #get rank for corresponding ID
    elif data['text'] == "!LRPerLG":
      #get rank for corresponding ID
    elif data['text'] == "!LRPerLGrank":
      #get rank for corresponding ID
    elif data['text'] == "!LRPerMS":
      #get rank for corresponding ID
    elif data['text'] == "!LRPerMSrank":
      #get rank for corresponding ID
    elif data['text'] == "!help":
      msg = "!LR"+"\n"+"!LG"+"\n"+"!LRrank"+"\n"+"!LGrank"+"\n"+"!LRPerLG"+"\n"+"!LRPerLGrank"+"\n"+"!LRPerMS"+"\n"+"!LRPerMSrank"+"\n"+"!help"
  send_message(msg)
    #msg = 'Recieved {}'.format(data))
    #msg = '{}, you sent "{}".'.format(data['name'], data['text'])

  return "ok", 200

def send_message(msg):
  url  = 'https://api.groupme.com/v3/bots/post'

  data = {
          'bot_id' : os.getenv('GROUPME_BOT_ID'),
          'text'   : msg,
         }
  request = Request(url, urlencode(data).encode())
  json = urlopen(request).read().decode()
  
def log(msg):
  print(str(msg))
  sys.stdout.flush()
