import os
import sys
import json

from urllib.parse import urlencode
from urllib.request import Request, urlopen
import FormatFilePY

from flask import Flask, request

app = Flask(__name__)


@app.route('/', methods=['POST'])
def webhook():
  data = request.get_json()
  log('Recieved {}'.format(data))

  # We don't want to reply to ourselves!
  #if personDict.has_key(data[id()])
  #FormatFilePY.makePersonDict()
  if data['name'] != 'Pythonbot':
    msg = "recognizes you sent message"
    if data['id'] in FormatFilePY.getPersonDict():
      if data['text'] == "!LR":
        msg = data['name']+" LR:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(1))
      elif data['text'] == "!LR rank":
        msg = data['name']+" LR rank:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(2))
      elif data['text'] == "!LG":
        msg = data['name']+" LG:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(3))
      elif data['text'] == "!LG rank":
        msg = data['name']+" LG rank:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(4))
      elif data['text'] == "!LRPerLG":
        msg = data['name']+" LR per LG:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(5))
      elif data['text'] == "!LRPerLGrank":
        msg = data['name']+" LR per LG rank:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(6))
      elif data['text'] == "!LRPerMS":
        msg = data['name']+" LR per MS:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(7))
      elif data['text'] == "!LRPerMSrank":
        msg = data['name']+" LR per MS rank:",FormatFilePY.getPersonDict().get(data['id'].__getitem__(8))
      elif data['text'] == "!help":
        msg = "!LR" + "\n" + "!LG" + "\n" + "!LRrank" + "\n" + "!LGrank" + "\n" + "!LRPerLG" + "\n" + "!LRPerLGrank" + "\n" + "!LRPerMS" + "\n" + "!LRPerMSrank" + "\n" + "!help"
    send_message(msg)
  # msg = 'Recieved {}'.format(data))
  # msg = '{}, you sent "{}".'.format(data['name'], data['text'])

  return "ok", 200


def send_message(msg):
  url = 'https://api.groupme.com/v3/bots/post'

  data = {
    'bot_id': os.getenv('GROUPME_BOT_ID'),
    'text': msg,
  }
  request = Request(url, urlencode(data).encode())
  json = urlopen(request).read().decode()


def log(msg):
  print(str(msg))
  sys.stdout.flush()
