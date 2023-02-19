#![allow(dead_code)]

use server::Server;
use std::env;
// use http::Request;
// use http::Method;
use website_handler::WebsiteHandler;

mod server; // compiler will reaad file
mod http;
mod website_handler;

fn main() {
    // let string = String::from("127.0.0.1:8080");// string have some value we use string ::from 
    // let string_slice = &string[10..]; // slicing strings ,after 10  byte
    // dbg!(&string);
    // dbg!(string_slice);

    // String slice is a view inside of existing string 
    // how to specify range in RUST

    // let get = Method::GET;
    // let delete = Method::DELETE;
    // let post = Method::POST;
    // let put = Method::PUT;

    println!("Lets run Server");
    let default_path = format!("{}/public", env!("CARGO_MANIFEST_DIR")); // read env variables at compile time
    let public_path = env::var("PUBLIC_PATH").unwrap_or(default_path);
    let server = Server::new("127.0.0.1:8080".to_string());
    server.run(WebsiteHandler::new(public_path)); // never return wait for new tcp conneciton
}
// this is single thread and use multithread module sync
// tokio 





