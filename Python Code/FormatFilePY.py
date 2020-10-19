import re, json, requests

personDict = {}
# "C:\\Users\\brandon\\Desktop\\FinalPersonData.json"

url = 'https://raw.githubusercontent.com/Mr-Bari-99/Pythonbot/main/FinalPersonDataPYTHON.json?token=ADZ344HOGQSGXWDSQLNZN2S7RX24G'
resp = requests.get(url)
data = json.loads(resp.text)
#print(data)


with open(data, 'r') as rf:
    for line in rf:
        variableList = line.split(',')
        id = variableList[0]
        variableList.pop(0)
        personDict[id] = variableList

    #print(personDict.get("35762170"))
    #print(personDict.values())
    #print(personDict.get("35762170").__getitem__(8))


#{
#    "attachments": [],
#    "avatar_url": "http://i.groupme.com/123456789",
#    "created_at": 1302623328,
#    "group_id": "1234567890",
#    "id": "1234567890",
#    "name": "John",
#    "sender_id": "12345",
#    "sender_type": "user",
#    "source_guid": "GUID",
#    "system": false,
#    "text": "Hello world ☃☃",
#    "user_id": "1234567890"
#}
