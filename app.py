import os
import sys
import json

from urllib.parse import urlencode
from urllib.request import Request, urlopen
#import FormatFilePY

from flask import Flask, request

app = Flask(__name__)

def getPersonDict():
    personDict = {}
    # FinalPersonDataPYTHON.json
    # "C:\\Users\\brandon\\Desktop\\FinalPersonData.json"

    with open("FinalPersonDataPYTHON.json", 'r') as rf:
        for line in rf:
            variableList = line.split(',')
            id = variableList[0]
            variableList.pop(0)
            personDict[id] = variableList

        #print(personDict.get("35762170"))
        #print(personDict.values())
        #print(personDict.get("35762170").__getitem__(8))
        return personDict

@app.route('/', methods=['POST'])
def webhook():
  data = request.get_json()
  log('Recieved {}'.format(data))
  
  dict = getPersonDict()

  # We don't want to reply to ourselves!
  #if personDict.has_key(data[id()])
  #FormatFilePY.makePersonDict()
  if data['name'] != 'Pythonbot':
    msg = "recognizes you sent message"
    #if data['id'] in dict.keys():
    if data['text'] == "!LR":
      msg = dict.get(data['id']).__getitem__(0)+" LR:",dict.get(data['id'].__getitem__(1))
    elif data['text'] == "!LR rank":
      msg = dict.get(data['id']).__getitem__(0)+" LR rank:",dict.get(data['id'].__getitem__(2))
    elif data['text'] == "!LG":
      msg = dict.get(data['id']).__getitem__(0)+" LG:",dict.get(data['id'].__getitem__(3))
    elif data['text'] == "!LG rank":
      msg = dict.get(data['id']).__getitem__(0)+" LG rank:",dict.get(data['id'].__getitem__(4))
    elif data['text'] == "!LRPerLG":
      msg = dict.get(data['id']).__getitem__(0)+" LR per LG:",dict.get(data['id'].__getitem__(5))
    elif data['text'] == "!LRPerLGrank":
      msg = dict.get(data['id']).__getitem__(0)+" LR per LG rank:",dict.get(data['id'].__getitem__(6))
    elif data['text'] == "!LRPerMS":
      msg = dict.get(data['id']).__getitem__(0)+" LR per MS:",dict.get(data['id'].__getitem__(7))
    elif data['text'] == "!LRPerMSrank":
      msg = dict.get(data['id']).__getitem__(0)+" LR per MS rank:",dict.get(data['id'].__getitem__(8))
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
