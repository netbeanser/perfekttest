var wsClient = null;
var offset = 0;

function getOffsetParam(){
    var param = window.location.search.split('=')[1];
    if (param === null) return 0;
    var offs = parseInt(param);
    if (isNaN(offs)){
//        offset=0;
        return 0;
    } else {
//        offset=offs;
        return offs;
    }  

//    return offset;
}

function booksPageLoaded(totalRows){

    offs = getOffsetParam();  
    
    if (offs === 0){
        document.getElementById("prev").disabled=true; 
        document.getElementById("next").disabled=false;             
    } 
    if ((offs + 10) >= totalRows){ //max 10 records per page
        document.getElementById("prev").disabled=false; 
        document.getElementById("next").disabled=true;            
    }
    
    if (totalRows <= 10){ //max 10 records per page
        document.getElementById("prev").disabled=true; 
        document.getElementById("next").disabled=true;         
    }    
    
    if ('WebSocket' in window){
        wsClient = new WebSocket("ws://localhost:8080/perfekt/favchange");
    } else if ('MozWebSocket' in window) {
        wsClient = new MozWebSocket("ws://localhost:8080/perfekt/favchange");
    } else {
        wsClient = null;
        return;   
    }   
    
    wsClient.onopen = function(evt){
        console.log("WebSocket opened");        
        log("WebSocket connection established.Protocol");
    };
    
    wsClient.onclose = function(evt){
        log("WebSocket connection closed. Reason: " + evt.reason
                +" Code: "+evt.code);
        console.log("WebSocket connection closed. Reason: " + evt.reason
                +" Code: "+evt.code);
    };
    
    wsClient.onerror = function(){
        log("WebSocket error");
        console.log("WebSocket error");
    };    
    
    wsClient.onmessage = function(m){
        log("Received: " + m.data);
        console.log("Received: " + m.data);
    };
    
}

function booksPageUnloaded(){
if (wsClient !== null) wsClient.close();    
}

function favChanged(obj,username){
       
    if (wsClient === null)  {
        alert('WebSocket is not supported by this browser.');
        return;
    }  
    
    var msg = {};
    msg.book_id = obj.id;
    msg.username = username;
    msg.checked = obj.checked;
    
    var jsonMsg = JSON.stringify(msg);
    
    if (wsClient.readyState === 1) {
        wsClient.send(jsonMsg);
    } else {
        log("wsClient state is " + wsClient.readyState);
    }
}

//Next three function serve registration.jsp
function checkUserDataValid(form){
    if (false === isUsernameValid(form)) {
        return false;
    }
    if (false === isPasswValid(form)) {
        return false;
    }
    return true;
}

function isUsernameValid(form){
    var name = form.elements[0].value;
    name.trim();
    var pattern = /^[\w\d]+$/;
    if ((pattern.test(name) && (name.length>4) && (name.length<11))){
        return true;
    }else {
        alert("Username must be alphanumeric and 5-10 chars length!");
        return false;
    }
}    

function isPasswValid(form){
    var passw = form.elements[1].value;
    passw.trim();
    var pattern = /^[\w\d]+$/;
    if ((pattern.test(passw) && (passw.length>4) && (passw.length<11))){
        return true;
    }else {
        alert("User password must be alphanumeric and 5-10 chars length!");
        return false;
    }    
    
}

function log(message) {
    var console = document.getElementById('console');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(new Date()));
    p.appendChild(document.createTextNode(message));
    console.appendChild(p);
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;
}

//Tese two functions serve books.jsp
function nextClicked(obj,totalRows){
    if ((offset + 10) > totalRows) return;
    offset+=10;
    if (offset >= totalRows){
        document.getElementById("next").disabled=true;        
    } else {
        document.getElementById("next").disabled=false;                
    }
    document.getElementById("prev").disabled=false;
    
    var url = "http://"+window.location.host
    +window.location.pathname
    +"?offset="+offset;
    window.location.href=url;
}

function prevClicked(obj,totalRows){
    if ((offset - 10) <0) { offset = 0 ;}
    else {
        offset-=10;
    }
    if (offset <= 0){
        document.getElementById("prev").disabled=true;        
    } else {
        document.getElementById("prev").disabled=false;                
    } 
    document.getElementById("next").disabled=false;
    
    var url = "http://"+window.location.host
    +window.location.pathname
    +"?offset="+offset;
    window.location.href=url;
}

function prevNext(dir,totalRows){
    var offs = getOffsetParam();
    var m = parseInt(dir);
    if (isNaN(m)) return;
    if (m>=0) m=1; else m=-1;
    var newOffs = offs+m*10;

    if (newOffs <=0){
        NewOffs = 0;
    } else if (newOffs >= totalRows) return;
    
    var url = "http://"+window.location.host
    +window.location.pathname
    +"?offset="+newOffs;
    window.location.href=url;    
}