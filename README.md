# TrainerBookingService
The goal of this repo is to build basic backend + frontend to do simple trainer bookings



# Goal of the service?

Scenario
During the beginning of the COVID-19 pandemic, physical exercise was very difficult, especially during lock-down. Getting to the gym was not possible and still remains difficult.
We want you to design a booking page for the Online Personal Trainer as well as a RESTful API to support that. The idea is that the gym or trainer would have a website where clients could go and see what classes are available and book a slot. Yes, there may be fees and invoicing as part of signing up for a service like this, but don’t worry about that. Focus on how to present the classes on offer and how to book a class as easily as possible.
Consider:
- How many trainers are there at the gym?
- How to present all the classes and book simply?
- Filter types of classes?
- Filter specific trainers?
- Do you need a date calendar selector?
- How far in advance can you book a class?
- When are trainers available?

These considerations are listed to give you a better idea of what is required. Perhaps one of these considerations don’t align with your design; just give a short comment in the Readme file as to why.


#  What do you need to run this project?

- Maven 3
- Java 8+



# FAQ

## Why do I have extra Trainers_details table?
Ideally i don't like this and might need to add more thought about it. The main idea is if the
system gets high load on the trainer table because it will get queried often then the more specific information can 
be on seperate table. I would assume it won't be issue but it is something I thought about.

There is definitly a few fields missing like email,id number ect... I am keeping things simple and
it could be things that I add in the future.



## Why is there no client contact details?
I realised i might maybe want some of the clients details when they book a session with the trainer but
i decided to skip that, it is definitly something that should be added.

