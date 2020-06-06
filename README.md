# meetings

This microservice handles scheduling and removing of meetings

####To schedule a new meeting:<br>

<b>Method:</b> POST<br>
<b>URL:</b> /meetings<br>
<b>body:</b><br>
`{`

`"startTime": "2020-06-07T10:30:26.514+00:00",`

`"endTime": "2020-06-07T11:15:26.514+00:00",`

`"title": "important meeting 2"`

`}`
`


####To remove a meeting by title:<br>
<b>Method:</b> DELETE<br>
<b>URL:</b> /meetings/title<br>
<b>Params:</b> meetingTitle<br>

Example: 

DELETE

`http://localhost:8080/meetings/title?meetingTitle=important meeting 2`

####To remove a meeting by start time:
<b>Method:</b> DELETE<br>
<b>URL:</b> meetings<br>
<b>Params:</b> fromTime<br>

Example: 

`http://localhost:8080/meetings?fromTime=2020-06-07T10:30:26.514+00:00`


####To get the next scheduled meeting:

<b>method:</b> GET<br>
<b>URL:</b> /meetings/next<br>

