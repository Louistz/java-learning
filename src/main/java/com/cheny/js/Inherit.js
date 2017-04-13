/**
 * Created by chenyong on 17/4/12.
 */
/* 继承 */

////////////////////原型链////////////////
function SuperType(){
    this.property = true;
}
SuperType.prototype.getSuperValue = function () {
    return this.property;
}

function SubType() {
    this.subproperty = false;
}
SubType.prototype = new SuperType();
SubType.prototype.getSubValue = function () {
    return this.subproperty;
}

var instance = new SubType();
alert(instance.getSuperValue()); //true

//我们把SubType的原型链指向了SuperType的一个实例。 我们知道所有的引用类型都是默认继承了Object，这个也是通过原型链实现的。
//因此默认原型都会包含一个内部指针，指向Object.prototype

alert(instance instanceof Object) ;   //true
alert(instance instanceof SuperType); //true
alert(instance instanceof SubType);   //true

alert(Object.prototype.isPrototypeOf(instance));    //true;
alert(SuperType.prototype.isPrototypeOf(instance)); //true
alert(SubType.prototype.isPrototypeOf(instance));   //true


//谨慎定义方法
/*添加新方法*/
SubType.prototype.getSubValue = function () {
    return this.subproperty;
}
/*重写超类中的方法*/
SubType.prototype.getSuperValue = function () {
    return false;
}
//重写方法的时候不能使用字面量创建原型方法


/*但是原型链的问题依然存在,而且没有办法在不影响所有对象实例的情况下，向超类传递参数*/
function SuperType() {
    this.colors = ['red','blue','green'];
}
function SubType(){

}
SubType.prototype = new SuperType();

var instance1 = new SubType();
instance1.colors.push("black");

var instance2 = new SubType();
alert(instance2.colors); //red,blue,green,black ......


///////////////////////借用构造函数////////////////////////////
function SuperType() {
    this.colors = ['red','blue','green'];
}
function SubType(){
    //继承了SuperType
    //通过借调了超类的构造函数，通过call方法或者apply方法，实际上在(未来将要)新创建的SubType的实例的
    //环境下调用了SuperType的构造函数。 这样，每个SubType的实例都具有自己的colors属性的副本了。
    SuperType.call(this);
}

var instance1 = new SubType();
instance1.colors.push("black");

var instance2 = new SubType();
alert(instance2.colors); //red,blue,green

//1.传递参数
function SuperType(name) {
    this.name = name;
}
function SubType(){
    SuperType.call(this,"Jack");
    this.age = 29;
}

var instance1 = new SubType();
alert(instance1.name); //Jack
alert(instance1.age); //29

//2. 借用构造函数的问题: 方法都在构造函数中定义，那么函数的复用就无从谈起。
// 而且在超类的原型中定义的方法，对子类而言也是不可见的。

/////////////////////组合继承///////////////////////
function SuperType(name) {
    this.name = name;
    this.colors = ['red','blue','green'];
}
SuperType.prototype.sayName = function () {
    alert(this.name);
};

function SubType(name,age) {
    //继承属性
    SuperType.call(this,name);
    this.age = age;
}
//继承方法
SubType.prototype  = new SuperType();
SubType.prototype.constructor = SubType;
SubType.prototype.sayAge = function () {
    alert(this.age);
}


/////////////////////////原型式继承///////////////////////////
function object(o){
    function F(){}

    F.prototype = o;
    return new F();
}

//ECMAScript5 新增Object.create方法，规范了原型式继承
var person = {
    name : "Nicholas",
    colors:['red','blue','green']
}

var anotherPerson = Object.create(person);//
anotherPerson.name = "Greg";
anotherPerson.colors.push("yellow");

var yetAnotherPerson = Object.create(person); // 这个地方取了同一个person作为原型。
yetAnotherPerson.name = "Linda";   //yetAnotherPerson自身的属性name
yetAnotherPerson.colors.push("black"); //从yetAnotherPerson中找到colors(最后在其原型中找到)，然后push。
// 实际也就push在了person中

alert(person.colors);  //red,blue,green,yellow,black

/*包含引用类型值得属性始终都会共享相应的值*/

///////////////////////寄生式继承//////////////////////////////
function createAnother(original){
    var clone = object(original);
    clone.sayHi = function () {
        alert("hi");
    }
    return clone;
}



///////////////////////寄生组合式继承///////////////////////////
//组合继承
function SuperType(name){
    this.name = name;
    this.colors = ['red','blue','green'];
}
SuperType.prototype.sayName = function () {
    alert(this.name);
}

function SubType(name,age) {
    SuperType.call(this,name);
    this.age = age;
}

SubType.prototype = new SuperType();
SubType.prototype.constructor = SubType;
SubType.prototype.sayAge = function () {
    alert(this.age);
}

//所谓寄生组合式继承，就是通过借用构造函数来继承属性，通过原型链的混成形式继承方法。
//
function inheritPrototype(subType, superType) {
    var prototype = object(superType.prototype);
    prototype.constructor = subType;
    superType.prototype = prototype;
}
