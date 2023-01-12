use super::method::{MethodError, Method};
//everything in module is private 
use std::error::Error;
use std::convert::TryFrom;
use std::str::{self, Utf8Error};
use super::{QueryString, QueryStringValue};

// use std::fmt::Display;
// use std::fmt::Formatter;
use std::fmt::{Display,Formatter, Result as FmtResult, Debug};

#[derive(Debug)]
pub struct Request<'buf> { 
    path : &'buf str,
    query_string : Option<QueryString<'buf>>, // Generic Option
    method : Method,
}

impl <'buf>Request<'buf>{
    // fn from_byte_array(buf : &[u8]) -> Result<Self,String> {} // used for type convert module std::convert
    pub fn path(&self) -> &str{
        &self.path
    }
    pub fn method(&self) ->&Method{
        &self.method
    }
    pub fn query_string(&self) -> Option<&QueryString> {
        self.query_string.as_ref()
    }

}

impl<'buf> TryFrom<&'buf [u8]> for Request<'buf>{
    type Error = ParseError;
// fn try_from(buf:&[u8]) -> Result<Self,Self::Error>{
    fn try_from(buf:&'buf [u8]) -> Result<Request<'buf>,Self::Error>{
        // let string = String::from("asd");
        // string.encrypt();
        // buf.encrypt();\

        // //number 1 
        // match str::from_ut8(buf){
        //     Ok(request) => {

        //     }
        //     Err(_) => return Err(ParseError::InvalidEncodng),
        // }

        // //number 2
        // match str::from_utf8(buf).or(Err(ParseError::InvalidEncoding)){
        //     Ok(request) => {
        //     }
        //     Err(e) => return Err(e),
        // }

        //number 3
        let request = str::from_utf8(buf)?;
        // match get_next_word(request){
        //     Some((method,request))=> {},
        //     None => return Err(ParseError::InvalidRequest),
        // }
        let (method, request) = get_next_word(request).ok_or(ParseError::InvalidRequest)?; // it will look at option 
        let (mut path, request) = get_next_word(request).ok_or(ParseError::InvalidRequest)?;
        let (protocol, _) = get_next_word(request).ok_or(ParseError::InvalidRequest)?;

        if protocol != "HTTP/1.1"{
            return Err(ParseError::InvalidProtocol); 
        }
        let method: Method = method.parse()?;
        let mut query_string = None;

        if let Some(i) = path.find('?'){
            query_string = Some(QueryString::from(&path[i + 1 ..])); // + 1byte 
            path = &path[..i]; 
        }
        Ok(Self {
            path,
            query_string,
            method,
        })

    }
}


// fn get_next_word<'a, 'b>(request: &'a str, b : &'b str) -> Option<(&'a str, &'b str)> 
fn get_next_word(request: &str) -> Option<(&str, &str)> {
    // let mut iter = request.chars();
    // loop {
    //     let item = iter.next();
    //     match item {
    //         Some(c) => {
    //         }
    //         None => break,
    //     }
    // }
    for (i, c) in request.chars().enumerate(){
        if c == ' ' || c == '\r' {
            return Some((&request[..i], &request[i + 1..]));
        }

    }

    None 
}
// to represent different typs of Error We use Enum 

// trait Encryot{
//     fn encrypt(&self) -> Self;
// }

// impl Encrypt for String{
//     fn encrypt(&self) -> Self{
//         unimplemented!()
//     }
// }
// impl Encrypt for &[u8] {
//     fn encrypt(&self) -> Self{
//         unimplemented!()
//     }
// }

impl Debug for ParseError{ 
    fn fmt(&self, f: &mut Formatter) -> FmtResult {
        write!(f,"{}", self.message())
    }
}
impl Display for ParseError{ 
    fn fmt(&self, f: &mut Formatter) -> FmtResult {
        write!(f,"{}", self.message())
    }
}

impl From<MethodError> for ParseError{
    fn from (_: MethodError) -> Self{
        Self::InvalidMethod
    }
}

// for number 3 
impl From<Utf8Error> for ParseError{
    fn from (_: Utf8Error) -> Self{
        Self::InvalidEncoding
    }
}

pub enum ParseError{
    InvalidRequest,
    InvalidEncoding,
    InvalidProtocol,
    InvalidMethod,
}
impl ParseError{
    fn message(&self) -> &str{
        match self {
            Self::InvalidRequest =>  "Invalid Request",
            Self::InvalidEncoding =>  "Invalid Encoding",
            Self::InvalidProtocol =>  "Invalid Protocol",
            Self::InvalidMethod =>  "Invalid Method",
        }
    }
}
impl Error for ParseError{

}