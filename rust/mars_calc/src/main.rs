use std::io;

fn main() {
    println!("Enter ur weight");
    let mut input_str = String ::new(); // String is  struct and create new string 
    // some_fn(&mut input);
    io::stdin().read_line(&mut input_str).unwrap(); // &mut ? : 
    // * Rust's main feature = ownership 
    // 1. Each value in Rust owned by variable (러스트에서 각각의 값은 변수가 소유한다)
    // 2. When the owner goes out of the scope, the value will be deallocated 
    // 3. There can only be ONE owner at a time - string cant be used for smae val but number can 

    println!("Input: {}", input_str);
    let weight: f32 = input_str.trim().parse().unwrap();
    let mars_weight = calculate_weight_on_mars(100.0); 
    // not specify variable type is okay
    // println!("Number : {}, String {} ", 100, "abcd"); // macro - meta programming 
    // we have to put mut to make variable mutable
    println!("Weight on Mars {}kg", mars_weight)
}

fn calculate_weight_on_mars(weight: f32) -> f32 {
    (weight/9.81)* 3.711 // no return ; needed 
}

fn some_fn(s: &mut String){ // passing references as parameters is borrowing 
    // in the same scope we can have as many immutable references as we want 

}