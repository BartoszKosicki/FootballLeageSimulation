# FootballLeageSimulation

The requirement was to create a football league simulator that should:
1) create teams 
2) schedule matches
3) simulate matches 
4) save the results to a separate txt file. 

The project I made also includes a model for determining the most optimal tactics (out of five available). The algorithm takes into account the sum of the potentials of randomly generated players. 

The match simulation consists of several steps. First, the algorithm determines the tactics, then in the first half the home team, which has a slight advantage on the pitch, attacks the visiting team in the second half. 

At the end of the match, points are added for the result of the match, and then possible suspensions of players due to aggressive behaviour or their absence due to injury are taken into account, which has an impact in subsequent matches. 
