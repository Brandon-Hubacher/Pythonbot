import os
import sys
import json

from urllib.parse import urlencode
from urllib.request import Request, urlopen
#import FormatFilePY

from flask import Flask, request

app = Flask(__name__)

#dict = getPersonDict()
@app.route('/', methods=['POST'])
def webhook():
  data = request.get_json()
  log('Recieved {}'.format(data))
  
  #if bool(dict) == false:
  #  dict = getPersonDict()

  # We don't want to reply to ourselves!
  #if personDict.has_key(data[id()])
  #FormatFilePY.makePersonDict()
  if data['name'] != 'Pythonbot':
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
    
    dict = getPersonDict()
    msg = "recognizes you sent message"
    #if data['id'] in dict.keys():
    if data['text'] == "!LR":
      #msg = "hi "+len(dict).__str__()
      #msg = data['name']+" LR:",dict.get(data['id'])
      msg = "hi"
    elif data['text'] == "!LR rank":
      #msg = data['name']+" LR rank:",dict.get(data['id'].__getitem__(2))
      #msg = "hi "+data['id]
      msg = "what LG"
    elif data['text'] == "!LG":
      msg = data['name']+" LG:",dict.get(data['id'].__getitem__(3))
    elif data['text'] == "!LG rank":
      msg = data['name']+" LG rank:",dict.get(data['id'].__getitem__(4))
    elif data['text'] == "!LRPerLG":
      msg = data['name']+" LR per LG:",dict.get(data['id'].__getitem__(5))
    elif data['text'] == "!LRPerLGrank":
      msg = data['name']+" LR per LG rank:",dict.get(data['id'].__getitem__(6))
    elif data['text'] == "!LRPerMS":
      msg = data['name']+" LR per MS:",dict.get(data['id'].__getitem__(7))
    elif data['text'] == "!LRPerMSrank":
      msg = data['name']+" LR per MS rank:",dict.get(data['id'].__getitem__(8))
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
