def makePersonDict():
    personDict = {}

    with open("FinalPersonDataPYTHON.json", 'r') as rf:
        for line in rf:
            variableList = line.split(',')
            id = variableList[0]
            variableList.pop(0)
            personDict[id] = variableList

        return personDict


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
