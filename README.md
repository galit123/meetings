# meetings

This microservice handles scheduling of meetings

To schedule a new meeting:
==========================

method: POST 
URL: /meetings
body:
{
"startTime": "2020-06-07T10:30:26.514+00:00",
"endTime": "2020-06-07T11:15:26.514+00:00",
"title": "important meeting 2"
}


To remove a meeting by title:
=============================
method: DELETE
URL: /meetings/title
Params: meetingTitle

example: 
DELETE
http://localhost:8080/meetings/title?meetingTitle=important meeting 2

To remove a meeting by start time:
==================================
method: DELETE
URL: meetings
Params: fromTime

example: http://localhost:8080/meetings?fromTime=2020-06-07T10:30:26.514+00:00


to get the next scheduled meeting:
==================================
method: GET
URL: /meetings/next

