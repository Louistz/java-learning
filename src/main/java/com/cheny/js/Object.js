/**
 * Created by chenyong on 17/4/12.
 */
/////////////////////////////创建对象///////////////////////
function createPerson(name,age,job){
    var o = new Object();
    o.name = name;
    o.age = age;
    o.job = job;
    o.sayName = function(){
        alert(this.name);
    }

    return o;
}
/*虽然创建了多个相似对象，但没有解决对象识别问题*/

///////////////////构造函数模式//////////////////////////
function Person(name,age,job){
    this.name = name;
    this.age = age;
    this.job = job;
    this.sayName = function(){
        alert(this.name);
    }
}
var p1 = new Person("Tom",10,"cat");
var p2 = new Person("Jerry",9,"mouse");
p1 instanceof Object ;//true
p2 instanceof Object ;//true
p1 instanceof Person ;//true
p2 instanceof Person ;//true
p1.constructor == Person //true
p2.constructor == Person //true

/**
 1.创建一个对象
 2.将构造函数的作用域赋给新对象(this就指向了新对象)
 3.执行构造函数(为对象添加属性)
 4.返回新对象

 构造函数模式的问题:
 每个方法都要在每个实例创建一遍
 */

//////////////////////////////原型模式//////////////////////////////
function Person(){

}
Person.prototype.name = "Bob"
Person.prototype.age = 29;
Person.prototype.job = "IT"
Person.prototype.sayName = function(){
    alert(this.name);
};

var p1 = new Person();
var p2 = new Person();

alert(p1.name); //Bob  Person-prototype.name
alert(p2.name); //Bob  Person-prototype.name

p1.name = "Tom"   //为对象新增属性name  p1.name
p2.name = "Jerry" //为对象新增属性name  p2.name

alert(p1.name);  //Tom    p1.name
alert(p2.name);  //Jerry  p2.name

/**
 理解原型对象：
 1.创建一个新函数，就会根据特定规则为该函数创建一个prototype属性(Person.prototype)
 2.这个prototype属性指向函数的原型对象(Person prototype)
 3.原型对象有一个constructor属性，它指向函数的指针。(Person-prototype.constructor -> Person())

 hasOwnProperty() 检测属性是否属于对象实例(p1.hasOwnProperty("name"))
 Object.getOwnPropertyDescriptor(p1/p2/Person, "name")  获取实例对象属性或者原型属性的描述符
 in 操作。 返回实例对象能够访问的属性(包括原型对象上的属性)

 Object.keys(p1) 接受一个对象，返回包含所有可枚举属性的字符串数组 [name,age,job,sayName]
 */
function hasPrototypeProperty(object , property){
    return !object.hasOwnProperty(property) && (property in object);
}

//////////////////////////更简单的原型语法///////////////////
function Person(){

}
Person.prototype = {
    name : "Bob",
    age  : 29,
    job  : "It",
    sayName: function(){
        alert(this.name);
    }
};
/*
问题:相当于我们重写了Person.prototype,constructor属性变成了新对象的constructor属性。
*/
var friend = new Person();
alert(friend instanceof Object) ; //true
alert(friend instanceof Person);  //true
alert(friend.constructor == Person) // false
alert(friend.constructor == Object) //true

/*因此，应该重新指定Person.prototype 的 constructor*/
function Person(){

}
Person.prototype = {
    constructor: Person,
    name : "Bob",
    age  : 29,
    job  : "It",
    sayName: function(){
        alert(this.name);
    }
};
/*但是,又会导致constructor的属性的Enumerable特性被设置为true,默认是false,因此*/
Object.defineProperty(Person.prototype,"constructor",{
    enumerable: false,
    value : Person
});

/////////////////////原型动态性///////////////////////
var friend = new Person();
Person.prototype.sayHi = function() {
    alert('Hi');
};
friend.sayHi(); //Hi
/*
但是如果重写了原型对象，就不行了。相当于friend已经指向了之前原型，而Person的原型被重写后指向了另一个对象。
friend指向的原型对象并没有变
*/
function Person(){}
var friend = new Person();

Person.prototype = {
    constructor: Person,
    name : "Bob",
    age  : 29,
    job  : "It",
    sayHi: function(){
        alert("Hi");
    }
};
friend.sayHi();  //error!!!!!

//原声对象也是这种模式创建的，因此可以通过修改原型，修改原生对象。
String.prototype.sayHi = function(){
    alert("Hi");
};
var msg = "JIM";
msg.sayHi();  //Hi

//////////////////////////原型模式的问题////////////////////////////
//属性共享造成问题，如果属性时个数组，一个实例往数组中加入了元素，其他对象也会看到。
// 因此要组合使用 构造函数模式和原型模式。
function Person(name,age,job){
    this.name = name;
    this.age = age;
    this.job = job;
    this.friends = ["Shelby","Court"];
}
Person.prototype = {
    constructor: Person,
    sayName: function(){
        alert(this.name);
    }
};

var p1= new Person("Nicholas",29,"Software Engineer");
var p2 = new Person("Greg",27,"Doctor");

p1.friends.push("Van");
alert(p1.friends); //Shelby,Court,Van
alert(p2.friends); //Shelby,Court
alert(p1.friends === p2.friends) ;//false
alsert(p1.sayName === p2.sayName);//true

///////////////////动态原型//////////////////////////
function Person(name,age,job){
    this.name = name;
    this.age = age;
    this.job = job;
    this.friends = ["Shelby","Court"];

    if(typeof this.sayName != 'function'){
        Person.prototype.sayName = function(){
            alert(this.name);
        }
    }
}
/////////////////////寄生构造函数///////////////////////////
function Person(name,age,job){
    var o = new Object();
    o.name = name;
    o.age = age;
    o.job = job;
    o.sayName = function(){
        alert(this.name);
    }

    return o;
}
var p = new Person("Nicholas",29,"Software Engineer");

function SpecialArray(){
    var values = new Array();

    values.push.apply(values,arguments);

    values.toPipedString = function(){
        return this.join(" | ");
    }
    return values;
}

var colors = new SpecialArray("red","blue","green");
alert(colors.toPipedString()); // red | blue | green

///////////////稳妥构造函数模式/////////////////////////////
//除了sayName外，无法访问name
function Person(name,age,job){

    var o = new Object();

    o.sayName = function(){
        alert(name);
    };
    return o;
}









