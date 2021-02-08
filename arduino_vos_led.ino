
#define a 13
#define b 12
#define c 11
#define d 10

char datosRx;

void setup() {
 pinMode(a,OUTPUT);
 pinMode(b,OUTPUT);
 pinMode(c,OUTPUT);
 pinMode(d,OUTPUT);
 Serial.begin(9600);  
}

void loop() {

  if(Serial.available()>0){
    
    datosRx = Serial.read();

    if(datosRx == 'a'){
      digitalWrite(a,HIGH); 
      digitalWrite(b,LOW);
      digitalWrite(c,LOW);
      digitalWrite(d,LOW);
    }
    if(datosRx == 'b'){
      digitalWrite(a,LOW);
      digitalWrite(b,HIGH);
      digitalWrite(c,LOW);
      digitalWrite(d,LOW);
    }
    if(datosRx == 'c'){
      digitalWrite(a,LOW);
      digitalWrite(b,LOW);
      digitalWrite(c,HIGH);
      digitalWrite(d,LOW);
    }
    if(datosRx == 'd'){
      digitalWrite(a,LOW);
      digitalWrite(b,LOW);
      digitalWrite(c,LOW);
      digitalWrite(d,HIGH);
    }
  }
  
}
