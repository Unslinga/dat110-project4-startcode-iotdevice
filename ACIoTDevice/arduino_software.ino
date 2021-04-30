int button_left = 3;
int button_right = 2;

int sensor = 13;

int led_grn = 5;
int led_ylw = 6;
int led_red = 7;

int combination[] = {1, 2};
int counter = 0;

int locked = 1;
int waiting = 0;


const int waiting_time = 6000;
const int lockout_time = 12000;
int waiting_current = 0;
int lockout_current = 0;

void setup()
{
  Serial.begin(9600);
  pinMode(button_left, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(button_left), combination_left, CHANGE);
  pinMode(button_right, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(button_right), combination_right, CHANGE);

  pinMode(sensor, INPUT);

  pinMode(led_grn, OUTPUT);
  pinMode(led_ylw, OUTPUT);
  pinMode(led_red, OUTPUT);
  Serial.write("SETUP COMPLETE!\n");
}

void loop()
{
  int sensor_val = digitalRead(sensor);

  sense(sensor_val);

  signal();

  timeout_timing();
  lockout_timing();
}

void signal()
{
  digitalWrite(led_grn, 1 - locked);
  digitalWrite(led_ylw, waiting);
  digitalWrite(led_red, locked);
}

void sense(int sensor_val)
{
  if (waiting == 0 && sensor_val == HIGH)
  {
    Serial.write("got sensor input.\n");
    waiting = 1;
    set_timeout();
  }
}

void set_timeout()
{
  waiting_current = millis();
}

void timeout_timing()
{
  if (waiting == 0)
  {
    return;
  }

  if ((millis() - waiting_current) >= waiting_time)
  {
    waiting = 0;
    Serial.write("timed out\n");
  }
}

void combination_left()
{
  if (digitalRead(button_left) == LOW)
  {
    return;
  }
  try_combination(1);
  Serial.write("left button\n");
}

void combination_right()
{
  if (digitalRead(button_right) == LOW)
  {
    return;
  }
  try_combination(2);
  Serial.write("right button\n");
}

void try_combination(int val)
{
  if (waiting == 0)
  {
    return;
  }

  if (locked == 0)
  {
    return;
  }

  if (val == combination[counter])
  {
    counter++;
    Serial.write("incremented\n");
    check_correct();
    return;
  }

  Serial.write("reset\n");
  counter = 0;
}

void check_correct()
{
  if (counter == combination_size())
  {
    set_lockout();
    locked = 0;
    counter = 0;
    waiting = 0;
    Serial.write("CORRECT!\n");
  }
}

int combination_size()
{
  return (sizeof(combination) / sizeof(combination[0]));
}

void set_lockout()
{
    lockout_current = millis();
}

void lockout_timing()
{
  if (locked == 1)
  {
    return;
  }

  if ((millis() - lockout_current) >= lockout_time)
  {
    locked = 1;
    Serial.write("locked out\n");
  }
}
