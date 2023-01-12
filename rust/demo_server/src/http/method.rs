use std::str::FromStr;

#[derive(Debug)]
pub enum Method {// rust enum is little different similar with union in C associate different tyoe
    GET,
    DELETE,
    POST,
    PUT,
    HEAD,
    CONNECT,
    OPTIONS,
    TRACE,
    PATCH,
}
// RUST는 NULL이 없지만, NULL의 개념을 새길 수 있는 enum을 가지고 있다. 그 enum이 바로 Option<T> 이다. 

impl FromStr for Method{
    type Err = MethodError;
    fn from_str(s: &str) -> Result<Self, Self::Err>{
        match s {
            "GET" => Ok(Self::GET),
            "DELETE"=> Ok(Self::DELETE),
            "POST"=> Ok(Self::POST),
            "HEAD"=> Ok(Self::HEAD),
            "OPTIONS"=> Ok(Self::OPTIONS),
            "PUT"=> Ok(Self::PUT),
            "CONNECT"=> Ok(Self::CONNECT),
            "TRACE"=> Ok(Self::TRACE),
            "PATCH"=> Ok(Self::PATCH),
            _ => Err(MethodError),
        }
    }
}

pub struct MethodError;
