import os
import sys
import json

from urllib.parse import urlencode
from urllib.request import Request, urlopen

from flask import Flask, request

def getPersonDict():
      personDict = {}

      with open("FinalPersonData.json", 'r') as rf:
          for line in rf:
              variableList = line.split(',')
              id = variableList[0]
              variableList.pop(0)
              personDict[id] = variableList
      return personDict
    
dict = getPersonDict()    

app = Flask(__name__)

@app.route('/', methods=['POST'])
def webhook():
  data = request.get_json()
  log('Recieved {}'.format(data))
  
  if data['name'] != 'Alfred':
    #msg = 
    if data['sender_id'] in dict.keys():
      if data['text'] == "!LR":
        msg = "Likes Received"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(1))
      elif data['text'] == "!LRRANK":
        msg = "Likes Received Rank"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(2))
      elif data['text'] == "!LG":
        msg = "Likes Given"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(3))
      elif data['text'] == "!LGRANK":
        msg = "Likes Given Rank"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(4))
      elif data['text'] == "!LRPERLG":
        msg = "Likes Received per Likes Given"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(5))
      elif data['text'] == "!LRPERLGRANK":
        msg = "Likes Received per Likes Given Rank"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(6))
      elif data['text'] == "!LRPERMS":
        msg = "Likes Received per Messages Sent"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(7))
      elif data['text'] == "!LRPERMSRANK":
        msg = "Likes Received per Messages Sent Rank"+"\n"+data['name']+": "+str(dict.get(data['sender_id']).__getitem__(8))
      elif data['text'] == "!HELP":
        msg = "!LR" + "\n" + "!LG" + "\n" + "!LRRANK" + "\n" + "!LGRANK" + "\n" + "!LRPERLG" + "\n" + "!LRPERLGRANK" + "\n" + "!LRPERMS" + "\n" + "!LRPERMSRANK" + "\n" + "!HELP"
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
