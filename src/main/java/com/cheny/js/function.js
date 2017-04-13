/**
 * Created by chenyong on 17/4/12.
 */
//闭包: 内部函数可以方位外部函数的变量，当允许内部函数被外部访问时，就拥有了访问这些内部变量的机会。
//闭包的两大用处： 一是可以读取函数内部的变量，而是让这些变量的值始终保存在内存中
function f1(){
    var n = 999;

    nAdd = function () {
        n+=1;
    }
    function f2() {
        alert(n);
    }
    return f2;
}

var result=f1();
result(); // 999
nAdd();
result(); // 1000

//闭包不能滥用，内存不能释放。解决的方法是在调用结束后将变量删除

//this ,随着函数使用场景的不同，this指的是调用函数的那个对象



/* this 对象是在运行时给予函数的执行环境绑定的。在全局函数中，this等于window. 而当函数被作为某个对象的方法调用时,
this等于那个对象。 不过匿名函数具有全局性，因此其this对象通常指向window.
 */

var name = "The window"
var object = {
    name : "myObject",
    getNameFunc: function () {
        return function () {
            //匿名函数，这里的this指向The window. 闭包后this保存在this.name中。
            return this.name;
        }
    }
}
alert(object.getNameFunc()()); //The window

////

var name = "The window"
var object = {
    name : "myObject",
    getNameFunc: function () {
        //这里我们把this给了that
        var that = this;
        return function () {
            //闭包可以访问that,而that引用object
            return that.name;
        }
    }
}
alert(object.getNameFunc()()); //myObject

////////
var name = "The window";
var object = {
    name : "My Object",
    getName: function () {
        return this.name;
    }
}

object.getName();  //MyObject
(object.getName)(); //MyObject     this得到维持，object.getName 和 (object.getName)的定义是相同的
(object.getName = object.getName)(); //The window 。 这个隐藏了一个复制语句的返回： var a = (object.getName = object.getName)
// a 的内容就是函数getName的内容。 就相当于 a(). 因此就是the window


/////////////////////////////////////////////////////////////模仿块级作用域///////////////////////////////////////////
//js是没有块级作用域的概念的。这意味着在块语句中定义的变量，实际上是在函数中定义的。

//js是不会告诉你是否多次声明了一个变量，它只会对后续的声明视而不见
function outputNumbers(count) {
    for (var i = 0; i< count;i++){
        alert(i);
    }
    alert(i);  //count

    var i; //即使重新声明了一个i，也不会改变其值
    alert(i); //count
}

//实现一个块级作用域(定义一个匿名函数并立即执行他)
(function(){

})();


function outputNumbers(count) {
    (function(){
        for (var i = 0; i< count;i++){
            alert(i);
        }
    })();

    alert(i);  //error!!!........
}

//这种技术通常在全局作用域中被用于函数外，避免向全级作用域添加过多变量和函数。
//这种做法可以减少闭包占用内存的问题。因为没有指向匿名函数的引用。只要函数指向完毕，就可以立即销毁其作用域链了。
(function(){
    var now = new Date();
    if(now.getMonth() == 0 && now.getDate() == 1){
        alert("Happy new Year");
    }

})();

///////////////////////////////////////////////////////私有变量//////////////////////////////////////////
//闭包
function MyObject() {
    var privateVar = 10;
    function privateFunc() {
        return false;
    }

    this.publicMethod = function () {
        privateVar++;
        alert(privateVar);
        return privateFunc();
    }
}

//利用私有和特权成员，隐藏不应该直接访问的数据
function Person(name) {
    this.getName = function () {
        return name;
    };
    this.setName = function(value){
        name = name;
    }
}
//缺点在于必须用构造函数模式，而构造函数模式的缺陷在于 每个实例都会创建新方法。 考虑是用静态私有变量来实现特权方法避免这个问题

////////////////////////////////////////////////////////静态私有变量/////////////////////////////////////////////////////
(function () {
    var privateVar = 10;
    function privateFunc() {
        return false;
    }
    MyObject = function () {

    };
    MyObject.prototype.publicMethod = function () {
        privateVar ++;
        return privateFunc();
    }
})();
//定义一个全局函数MyObject,利用其原型中的方法来访问局部变量privateVar和局部方法privateFunc()

(function () {
    var name = "";
    Person = function (value) {
        name = value;
    };
    Person.prototype.getName = function () {
        return name;
    };
    Person.prototype.setName = function (value) {
        name = value;
    };
})();
var p1 = new Person("jack");
alert(p1.getName); //jack
p1.setName("Tom");
alert(p1.getName()); //Tom

var p2 = new Person("Michael");
alert(p1.getName()); //Michael
alert(p2.getName()); //Michael
//即使我们制定了
Person.prototype.constructor = Person;
//但是因为他们使用的是同一个局部变量name ,所以问题还是会同样出现
//而且多查找作用域链中的一个层次，就会在一定程度影响查找速度。

/////////////////////////////////模块模式///////////////////////
//通过为单例创建私有变量和特权方法来实现模块模式
//js中的单例
var singleton = {
    name : "",
    method: function () {

    }
};
//模块模式
var singleton = function () {
    var privateVar = 10;
    function privateFunc() {
        return false;
    };

    return {
        publicProperty: true,
        publicMethod: function () {
            privateVar ++;
            alert(privateVar);
            return privateFunc();
        }
    }
}();

//模块模式维护了一个 私有的变量和私有方法。

/////////////////////////////////增强的模块模式///////////////////////
var singleton = function () {
    var privateVar = 10;
    function privateFunc() {
        return false;
    };
    //这样，操作类型必须是某个确定的类型
    var object = new CustomType();
    object.publicProperty = true;
    object.publicMethod = function () {
        privateVar ++;
        return privateFunc();
    };
    return object;
}();

var application = function() {
    //私有变量和函数
    var components = new Array();

    //初始化
    components.push(new BaseComponent());

    //创建application的一个局部副本
    var app = new BaseComponent();

    //公共接口
    app.getComponentCount = function () {
        return components.length;
    };
    app.registerComponent = function (component) {
        if (typeof component == "object") {
            components.push(component);
        }
    };
    return app;
}();