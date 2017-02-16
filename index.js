
var express = require('express')
var app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);

// Import the Guessit game file.
var guessit= require('./guessit');


// Reduce the logging output of Socket.IO
io.set('log level',1);


io.on('connection',function(socket){
    console.log('one user connected '+socket.id);
    guessit.initGame(io, socket);
    socket.on('message',function(data){
        var sockets = io.sockets.sockets;
        /*sockets.forEach(function(sock){
            if(sock.id != socket.id)
            {
                sock.emit('message',data);
            }
        })*/
        socket.broadcast.emit('message', data);
    })
    socket.on('disconnect',function(){
        console.log('one user disconnected '+socket.id);
    })
})



http.listen(process.env.PORT || 80800 ,function(){
    console.log('server listening on port ' + String(process.env.PORT));
})
