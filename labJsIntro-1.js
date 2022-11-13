// Jasharn Thiara
// TCSS 380 B
// 11/4/2022
// Lab JS Intro

console.log("hello world");

let numbers = [1,2,3,4,'5',6,"7"];
console.log(numbers);

let half = numbers.map(x => x / 2);
console.log(half);

let noThree = half.filter(x => x !== 3);
console.log(noThree);

function cuboidVolume(l, w) {
    return (h) => l * w * h;
}

let cubeMaker = cuboidVolume(4,5);
console.log(cubeMaker(10));

let cubes = numbers.map(cubeMaker);
console.log(cubes);
