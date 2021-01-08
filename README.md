# Top Score App Documentation

This web app is a score keeping web application. Clients can create scores associated with a player name. After creation the score can be retrieved and deleted. Score can also be retrieved by player names.

## API service end points

URL:
/score/save

Sample Request:
{
    "score": 5,
    "playerName": "testPlayer"
}

Sample Response:
{
    "scores": [
        5
    ],
    "page": 0,
    "totalElements": 0
}

<br />

URL:
/score/get

Query Parameters (type/format below):
playerNames : String / "testPlayer"
before : String / "2020-12-31T09:40:01Z"
after : String / "2020-12-31T09:40:01Z"
page :  int / 0
perPage : int / 1

Sample Request:
/score/get?playerNames=testplayer1,testplayer2&after=2020-12-31T09:45:01Z

Sample Response:
{
    "scores": [
        456,
        894,
        44,
        44,
        4854,
        4765854,
        65854
    ],
    "page": 0,
    "totalElements": 7
}

Sample Request:
/score/get?playerNames=testplayer1,testplayer2&before=2020-12-31T09:40:01Z&after=2020-12-31T09:45:01Z&page=1&perPage=5

Sample Response:
{
    "scores": [
        65854
    ],
    "page": 1,
    "totalElements": 6
}

<br />

URL:
/player/get

Query Parameters (type/format below):
playerName : String / "testPlayer"
before : String / "2020-12-31T09:40:01Z"
after : String / "2020-12-31T09:40:01Z"
page :  int / 0
perPage : int / 1

Sample Request:
/player/get?playerName=testplayer1

Sample Response:
{
    "scoreList": [
        {
            "id": 4,
            "createTime": "2021-01-08T01:06:45Z",
            "score": 456,
            "playerName": "testplayer1"
        },
        {
            "id": 5,
            "createTime": "2021-01-08T01:06:52Z",
            "score": 894,
            "playerName": "testplayer1"
        },
        {
            "id": 6,
            "createTime": "2021-01-08T01:07:01Z",
            "score": 44,
            "playerName": "testplayer1"
        }
    ],
    "page": 0,
    "totalElements": 3
}

<br />

URL:
/score/remove

Sample Request:
{
    "id": 4
}

Sample Response:
{
    "page": 0,
    "totalElements": 0
}

## Build

A gradle build file is provided in the root folder of the project. A gradle wrapper is also included for convenience.

Note: tests triggered by gradle will fail if Docker is not running on your machine.

## Deployment 

If deploying to production or local development the Web application requires a MySQL database to be running. The required table schema can be created from the file located at "\src\main\resources\init.sql". Please update the DB settings in application.properties when the MySQL instance is running.

## Unit / Integration Test

Unit test:
ScoreDomainTests

Integration test:
ScoreRepositoryTests
ScoreControllerTests

Note:
Integration testing requires Docker to be running on the test machine which creates Testcontainers instances of the MySQL DB used for testing.

P.S. There are more test senarios that needs to be covered but due to limited time to work on this the current tests should give good examples of Unit / Integration Test.