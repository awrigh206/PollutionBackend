(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1d163199"],{"73cf":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"register"},[n("div",{staticClass:"bg"}),n("div",{staticClass:"form-wrapper"},[n("Logo"),n("form",{staticClass:"register-form",on:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.lastPassFix(t)},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.submit()}}},[n("div",{staticClass:"title"},[e._v(e._s(e.form.title))]),n("section",{staticClass:"fields"},e._l(e.form.input,(function(t,r){return n("div",{key:r,staticClass:"input-wrapper",class:[e.hasFailed(t)],attrs:{id:e.formatInputId(r)}},[n("label",{attrs:{for:r}},[n("Icon",{attrs:{name:t.icon}}),n("span",{staticClass:"label"},[e._v(e._s(t.placeholder))])],1),"checkbox"===t.type?n("input",{directives:[{name:"model",rawName:"v-model",value:t.model,expression:"input.model"}],attrs:{id:r,name:r,type:"checkbox"},domProps:{checked:Array.isArray(t.model)?e._i(t.model,null)>-1:t.model},on:{change:function(n){var r=t.model,o=n.target,a=!!o.checked;if(Array.isArray(r)){var i=null,s=e._i(r,i);o.checked?s<0&&e.$set(t,"model",r.concat([i])):s>-1&&e.$set(t,"model",r.slice(0,s).concat(r.slice(s+1)))}else e.$set(t,"model",a)}}}):"radio"===t.type?n("input",{directives:[{name:"model",rawName:"v-model",value:t.model,expression:"input.model"}],attrs:{id:r,name:r,type:"radio"},domProps:{checked:e._q(t.model,null)},on:{change:function(n){return e.$set(t,"model",null)}}}):n("input",{directives:[{name:"model",rawName:"v-model",value:t.model,expression:"input.model"}],attrs:{id:r,name:r,type:t.type},domProps:{value:t.model},on:{input:function(n){n.target.composing||e.$set(t,"model",n.target.value)}}}),t.error?n("Icon",{directives:[{name:"tooltip",rawName:"v-tooltip",value:{type:"alert",text:t.error},expression:"{ type: 'alert', text: input.error }"}],staticClass:"error",attrs:{name:"warning"}}):e._e()],1)})),0),n("section",{staticClass:"form-control"},e._l(e.form.control,(function(t,r){return n("Button",e._b({key:r},"Button",t,!1),[e._v(e._s(t.text))])})),1)])],1)])},o=[],a=(n("4160"),n("13d5"),n("159b"),n("96cf"),n("1da1")),i=n("2ef0"),s=n.n(i),c=n("bc3a"),l=n.n(c),u=n("811d"),d=n("bbc9"),m=n("caa8"),p={components:{Logo:u["a"],Icon:d["a"],Button:m["default"]},data:function(){return{form:{title:"Register",action:"/user/register",input:{userName:{icon:"person",type:"text",placeholder:"USERNAME"},password:{icon:"lock",type:"password",placeholder:"PASSWORD"},email:{icon:"mail",type:"text",placeholder:"EMAIL"},number:{icon:"phone_msg",type:"text",placeholder:"PHONE NUMBER"}},control:{submit:{text:"Register",icon:"circle-right",type:"dialog",click:this.submit.bind(null)}}}}},activated:function(){this.clear()},methods:{lastPassFix:function(e){e.stopImmediatePropagation()},formatInputId:function(e){return"input-".concat(e)},hasFailed:function(e){return e.error?"failed":null},clear:function(){var e=this;s.a.forEach(this.form.input,(function(t,n){e.$delete(e.form.input[n],"model"),e.$delete(e.form.input[n],"error")}))},clean:function(){var e=this;s.a.forEach(this.form.input,(function(t,n){e.$delete(e.form.input[n],"error")}))},prepareInputData:function(){var e=s.a.reduce(this.form.input,(function(e,t,n){return e[n]=t.model,e}),{});return e},submit:function(){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function t(){var n,r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(!e.form.control.submit.pending){t.next=2;break}return t.abrupt("return");case 2:return e.clean(),n=e.prepareInputData(),t.prev=4,e.$set(e.form.control.submit,"pending",!0),t.next=8,l.a.post(e.form.action,n);case 8:if(r=t.sent,r.data){t.next=13;break}throw"Something went wrong";case 13:e.$store.commit("setUser",r.data),e.$router.push({name:"home"});case 15:t.next=20;break;case 17:t.prev=17,t.t0=t["catch"](4),s.a.forEach(e.form.input,(function(t,n){e.$set(e.form.input[n],"error","One of the fields is invalid!")}));case 20:e.$delete(e.form.control.submit,"pending");case 21:case"end":return t.stop()}}),t,null,[[4,17]])})))()}}},f=p,h=(n("a894"),n("2877")),v=Object(h["a"])(f,r,o,!1,null,null,null);t["default"]=v.exports},a894:function(e,t,n){"use strict";var r=n("db38"),o=n.n(r);o.a},db38:function(e,t,n){}}]);
//# sourceMappingURL=chunk-1d163199.9f355186.js.map