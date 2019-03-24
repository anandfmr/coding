# HeavenHR Coding Challenge

Coding Challenge From HeavenHR - Solved By Anand Singh

## Task description

Build a backend service that handles a (very simple) recruiting process. 
The process requires two types of objects: job offers and applications from candidates

## User cases

* user has to be able to create a job offer and read a single and list all offers.
* candidate has to be able to apply for an offer.
* user has to be able to read one and list all applications per offer.
* user has to be able to progress the status of an application.
* user has to be able to track the number of applications.
* status change triggers a notification

## Solution explanation

Using Spring and h2 (embedded DB)  I created the backend for this challenge.

The offer number of applications is calculated when selected from the DB.

## Starting project

There are two options: 
1. Run CodingApplication.java from an your IDE
2. Run maven package and run the command from the target folder: ``java -jar coding-0.0.1-SNAPSHOT.jar``

When you run the project it will run on your `localhost` on port `8080`.

Access DB by visiting following URL ```http://localhost:8080/h2-console```

## APIs

There are 6 API calls that we can make:

1. Create a job offer - a POST call to the URI ``/v1/offer`` with the body:
``{
    "jobTitle": "Sr. Developer",
    "startDate": "07/05/2020"
  }``
  
2. Get all job offers - a GET call to the URI ``/v1/offer``
Should return a list of all job offers.

3. Get a single job - a GET call to the URI ``/v1/offer/{job_title}`` with `{job_title}` being the offer job title.
If the offer doesn't exists return http not found

4. Apply for an application - a POST call to the URI ``/v1/application`` with body like 
``{
    "offer": "Sr. Developer",
    "candidateEmail": "aks@gmai.com",
    "resume":"dda"
  }``
  
  5. Get applications - a GET call to URI ``/v1/application`` to get all application.
  You can filter the applications by ``offer``/ ``email`` or ``applicationStatus`` the response is a list of all applications answering to the filters chosen.
  
  6. Progress application - a POST call to the URI ```/v1/applicationProgress``` with body:
  ``{
       "offer": "Sr. Developer",
       "candidateEmail": "aks@gmai.com",
       "applicationStatus":"INVITED"
     }``
     The response is the new application status . 
 
 ## Application progression explanation 
 * Application candidate can be "progressed" to any status.
 