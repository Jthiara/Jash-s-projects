/**
*TCSS 342
*
*Jasharn Thiara
*
*UW NetID: jthiara
*
*Winter 2021-2022
*/

A.)Time Complexity of encode, decode, equal in big-O notation. 

-Encode is o(n) since we need to iterate through a list n number of times. Decode is also o(n) because we need to iterate through a string and create
the linked list, and the string and list could by very large. The equal method is also o(n) the only significant statements are the calls to decode twice, which could be
considered o(2n), but ultimately o(n).

B.) What are the advantages and disadvantages of RLE? And its applications?

- I believe that one advantage could be the use of space with RLE. A linked list of 100 w's could be converted to 100w which is much smaller. I think one disadvantage is that 
ultimately this conversion from a list to a string has to be made at some point and could take very long if working with large amounts of data. It's application could be that 
converting between these two forms provides more flexibility and organization for companies that keep track of data. 

C.)Amount of time you spent on implementing the assignment? Challenges you faced
while implementing the assignment? How did you overcome these challenges? 

- I spent about 12 hours on this assignment. One of the bigger challenges I faced was running into unique cases where my methods did not work properly. Another obstacle was 
use of different functions of java I have not learned or used in a while. I overcame this by stepping back and considering how I could create solutions that would not just work 
with the basic cases, but for all of them. I also had to brush up on the notes from class and do my own research online and I think I ultimately learned more about programming
by struggling. 