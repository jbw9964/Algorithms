def solution(record):
    db = dict()         # uid : name
    result = []

    for log in record : 
        assert type(log) is str

        temp = log.split(" ")
        act, uid = temp[0], temp[1]

        if act == "Leave" : 
            result.append(["님이 나갔습니다.", uid])

        else : 
            name = temp[2]
            db[uid] = name

            if act == "Enter" : 
                result.append(["님이 들어왔습니다.", uid])
    
    answer = []
    for log, uid in result : 
        answer.append(db[uid] + log)

    return answer
