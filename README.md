# trenolandia
(In Spanish it means The land of the trains, just a little of humor)

enpoints
- POST: /api/arrivals/calculateDelay
params:
- plannedArrivals (.csv) 
- plannedArrivals (.csv)

Use cases:
- inspector of arrivals: 
    check the delay of each train when arrive to a determined station.
    filter the most delayed arrives.
- csv helper: provides write and read features to process our train records.

domains:
- PlainArrival: model the basic record received from the client, offering some behaviors related to its attributes
(I think they could be separated out of the class as alternative, but I liked inside because they are coupled with
its attributes)
- CheckedArrival: the post processed arrival record, that is going to be manipulated.

In this case I thought It would be worth to provide a rest API because is easy to manipulate via postman 
and extensible with any web or native front end app

I know that now I have 7 files, outgoing the recommended number of 6.
But I think in a real word, with more complicated models, would be worth of have separated 
Csv reader and writer to better understanding.

And to try to follow SOLID principles and Hexagonal architecture.
The controller better to depend on abstractions rather than implementations.

I hope it is no so bad, happy coding.