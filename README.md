# meetings

This microservice handles scheduling and removing of meetings

####To start the microservice:
* Go to the file: <i>'com/test/meetings/MeetingsApplication.java'</i><br>
* Right-click anywhere on the source<br>
* Select <i>'Run MeetingsApplication.main'</i>


####To schedule a new meeting:<br>

<b>Method:</b> POST<br>
<b>URL:</b> /meetings<br>
<b>Params:</b><br>
fromTime<br>
toTime<br>
meetingTitle<br>

Example:<br>
POST<br>
`http://localhost:8080/meetings?fromTime=2020-06-17T18:10:04.908Z&toTime=2020-06-17T20:10:04.908Z&meetingTitle=my meeting`

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

Example:<br>
DELETE<br>
`http://localhost:8080/meetings?fromTime=2020-06-01T19:10:12.479Z`


####To get the next scheduled meeting:

<b>Method:</b> GET<br>
<b>URL:</b> /meetings/next<br>

