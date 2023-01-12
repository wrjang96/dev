// every file is module
use std::net::TcpListener;
use std::io::{Read, Write};
use crate::http::{Request, Response, StatusCode, ParseError};
use std::convert::TryFrom;

pub trait Handler{
    fn handle_request(&mut self, request: &Request) -> Response;

    fn handle_bad_request(&mut self, e: &ParseError)-> Response{
        println!("Failed to parse a request : {}", e);
        Response::new(StatusCode::BadRequest, None)
    }
}
pub struct Server { // so weshould use pub 
    addr : String,
}

impl Server { // method is similar to general function, method is defined insde of struct
    pub fn new(addr : String) -> Self {
        Self {
            addr
        }

    }
    pub fn run (self, mut handler: impl Handler){ // self = this, run owns struct, to not deallocate use &, u can use &mut too
        println!("Listening on {}", self.addr);
        let listener = TcpListener::bind(&self.addr).unwrap();

        //rust has special for infite loop which is loop(while true)
        loop{
            //match expression
            match listener.accept() {
                Ok((mut stream, _)) => {
                    // initializing values 
                    let mut buffer = [0; 1024];
                    match stream.read(&mut buffer){
                        Ok(_)=>{
                            println!("Received a request {}", String::from_utf8_lossy(&buffer));
                            let response = match Request::try_from(&buffer[..]) {
                                Ok(request )=> handler.handle_request(&request),
                                // Ok(request )=> {
                                    // dbg!(request);
                                    // Response::new(
                                    //     StatusCode::Ok, 
                                    //     Some("<h1> IT WORKS </h1>".to_string()),
                                    // ) replaced with custom traits 

                                    // write!(stream, "{}", response);
                                    // response.send(&mut stream);;
                                // },
                                Err(e) => 
                                // {
                                    // println!("Failed to parse a request : {}", e);
                                    // Response::new(StatusCode::BadRequest, None)
                                    handler.handle_bad_request(&e),
                                // }
                            };

                            if let Err(e)= response.send(&mut stream) {
                                println!("Failed to send response: {}", e);
                            }
                        }
                        Err(e) => println!("Fail to establish connection : {} ", e)
                    }
                },
                Err(e) => println!("Fail to establish connection : {} ", e)
            }

            let res = listener.accept(); // tuple we cant modify size 
            // assure res is not error
            if res.is_err(){
                continue;
            }

        }
    }
}


