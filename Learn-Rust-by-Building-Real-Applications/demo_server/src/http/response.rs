use std::{fmt:: {Display, Formatter, Result as FmtResult}, net::TcpStream};
// use std::net::TcpStream;
use std::io::{Write,Result as IoResult};
use super::StatusCode;

// pub enum StatusCode {}

#[derive(Debug)]
pub struct Response {
    status_code : StatusCode,
    body: Option<String>,

}

impl Response
{
    pub fn new(status_code: StatusCode, body: Option<String>) -> Self {
        Response { status_code, body}
    }
    // pub fn send_TcpStream(&self, stream:&mut TcpStream) -> IoResult<()>{
    // pub fn send_File(&self, stream:&mut File) -> IoResult<()>{
    pub fn send(&self, stream:&mut impl Write) -> IoResult<()>{ // dynamic 
        let body = match &self.body {
                        Some(b) => b,
                        None => "",
                    };
            
                    write!(
                        stream, 
                        "Http/1.1 {} {}\r\n\r\n{}",
                        self.status_code,
                        self.status_code.reason_phrase(),
                        body
                    )
    }
}

// impl Display for Response{
//     fn fmt(&self, f: &mut Formatter) -> FmtResult{
//         let body = match &self.body {
//             Some(b) => b,
//             None => "",
//         };

//         write!(f, 
//             "Http/1.1 {} {}\r\n\r\n{}",
//             self.status_code,
//             self.status_code.reason_phrase(),
//             body
//         )

//     }
// }