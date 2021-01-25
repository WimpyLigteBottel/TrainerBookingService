# TrainerBookingService
The goal of this repo is to build basic backend + frontend to do simple trainer bookings



# Goal of the service?

Scenario
During the beginning of the COVID-19 pandemic, physical exercise was very difficult, especially during lock-down. Getting to the gym was not possible and still remains difficult.
We want you to design a booking page for the Online Personal Trainer as well as a RESTful API to support that. The idea is that the gym or trainer would have a website where clients could go and see what classes are available and book a slot. Yes, there may be fees and invoicing as part of signing up for a service like this, but don’t worry about that. Focus on how to present the classes on offer and how to book a class as easily as possible.
Consider:
- How many trainers are there at the gym? (Done - you can find all the trainers via /trainer)
- How to present all the classes and book simply? ( Incomplete )
- Filter types of classes?
- Filter specific trainers? ( Done - You are able to search by trainer name this could be improved if you have more specific type of train example Cardio ,BodyBuilding )
- Do you need a date calendar selector? (Incomplete - Ideally client will make a booking and instructor/trainer will accept the booking)
- How far in advance can you book a class? (Depends - It depends on the business case ideally you only want to book maybe 2 months maybe ahead?)
- When are trainers available? (Incomplete - Need to implete endoint to expose all the classes that are booked thus you can see when the trainers are busy)

These considerations are listed to give you a better idea of what is required. Perhaps one of these considerations don’t align with your design; just give a short comment in the Readme file as to why.


#  What do you need to run this project?

- Maven 3
- Java 8+



# FAQ

## Why do I have extra Trainers_details table?
Ideally i don't like this and might need to add more thought about it. The main idea is if the
system gets high load on the trainer table because it will get queried often then the more specific information can 
be on separate table. I would assume it won't be issue but it is something I thought about.

There is definitely a few fields missing like email,id number ect... I am keeping things simple and
it could be things that I add in the future.



## Why is there no client contact details?
I realised i might maybe want some of the clients details when they book a session with the trainer but
i decided to skip that, it is definitely something that should be added.

