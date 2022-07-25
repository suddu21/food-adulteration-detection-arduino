#define s0 8       //Module pins wiring
#define s1 9
#define s2 10
#define s3 11
#define out 12
#define metalDigIn 3
#define metalAnIn A0

int data=0;

void setup() 
{
   pinMode(s0,OUTPUT);    //pin modes
   pinMode(s1,OUTPUT);
   pinMode(s2,OUTPUT);
   pinMode(s3,OUTPUT);
   pinMode(out,INPUT);
   pinMode(metalDigIn, INPUT);
   pinMode(metalAnIn, INPUT);

   Serial.begin(9600);   //intialize the serial monitor baud rate
   
   digitalWrite(s0,HIGH); //Putting S0/S1 on HIGH/HIGH levels means the output frequency scalling is at 100% (recommended)
   digitalWrite(s1,HIGH); //LOW/LOW is off HIGH/LOW is 20% and LOW/HIGH is  2%
   
}

void loop()                  //Every 2s we select a photodiodes set and read its data
{

   /*digitalWrite(s2,LOW);        //S2/S3 levels define which set of photodiodes we are using LOW/LOW is for RED LOW/HIGH is for Blue and HIGH/HIGH is for green
   digitalWrite(s3,LOW);
   Serial.print("Blue value= "); 
   GetData();                   //Executing GetData function to get the value

   digitalWrite(s2,LOW);
   digitalWrite(s3,HIGH);
   //Serial.print("Red value= ");
   GetData();

   digitalWrite(s2,HIGH);
   digitalWrite(s3,HIGH);
   //Serial.print("Green value= ");
   GetData();*/

   //Metal detector part
   float Analog;
   int Digital;
   Serial.println();
   // Current value will be read and converted to the voltage
   Analog = analogRead (metalAnIn) * (1.0 / 1023.0);
   Digital = digitalRead (metalDigIn);
 
   // and outputted here
   Serial.print ("Analog voltage value:"); Serial.print (Analog, 4); Serial.print ("V, ");
   Serial.print ("Extreme value:");
   if(Digital==1)
     {
       Serial.println (" Digital O/P :- Metal present");
     }
   else
     {
       Serial.println (" Digital O/P :- Metal NOT present");
     }
   Serial.println ("----------------------------------------------------------------");

   Serial.println();

   delay(1000);
}

void GetData(){
   data=pulseIn(out,LOW);       //here we wait until "out" go LOW, we start measuring the duration and stops when "out" is HIGH again
   //Serial.print(data);          //it's a time duration measured, which is related to frequency as the sensor gives a frequency depending on the color
   //Serial.print("\t");          //The higher the frequency the lower the duration
   delay(20);
}
