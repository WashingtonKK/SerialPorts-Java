byte data [10] = {1, 2, 3 , 4, 5, 6, 7, 8, 9, 10};
int potval = 124;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {

      for (int j =0; j<10; j++){
        Serial.println(data[j]);
        delay(100);
        }
      Serial.println(potval);       
     
}
