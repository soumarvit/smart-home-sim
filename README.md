# omo_notsosmarthome

## TS1 semestral project
SmartHome also acts like a semestral project for TS1 course. Everything related to software testing is located in the 'testing' branch

## Description
SmartHome is an application that simulates a run of a smart home with a variety of devices, people and animals.

## Project
This project was created as a semestral project for a OMO course at CTU FEE.
The goal of this project was to learn how to write clean, reusable code with focus on applying design patterns in the right places.

## Info
Pri implementaci jsme pouzivali intellij funkci code with me, proto ma valnou vetsinu commitu na starosti Soumar.

## UML
Odkaz na UML class diagram a UML use case diagram: [Lucidchart](https://lucid.app/lucidchart/97bc368b-4599-4722-bebf-889dc40d96a2/edit?viewport_loc=-299%2C-534%2C4588%2C2270%2CdvQ3cUOME~3G&invitationId=inv_2f3fb955-21bf-4c51-94fb-bdb629ff5503)
<br/>PNG soubory se nachazi ve slozce uml.

## Funkcni pozadavky
<details><summary>Click here to show "Funckcni pozadavky"</summary>
<br/>

F1. Entity se kterými pracujeme je dům, okno (+ venkovní žaluzie), patro v domu, senzor, zařízení (=spotřebič), osoba, auto, kolo, domácí zvíře jiného než hospodářského typu, plus libovolné další entity

- tridy House, Floor, Room, Window, Sensor, Device, Vehicle, Person, Animal
___
F2. Jednotlivá zařízení v domu mají API na ovládání. Zařízení mají stav, který lze měnit pomocí API na jeho ovládání. Akce z API jsou použitelné podle stavu zařízení.

- trida DeviceController
- Device ma tri mozne stavy - on/off/idle mezi kterymi jde prepinat pomoci kontroleru. Toto je brano jako event a nasledne je to zapsano do Event reportu
- Device ma take promennou lifespan ktera kdyz klesne na hodnotu 0, tak se zarizeni rozbije a musi byt opraveno predtim, nez bude znovu pouzito

___
F3. Spotřebiče mají svojí spotřebu v aktivním stavu, idle stavu, vypnutém stavu

- tridy Consumption a ConsumptionData
- trida Consumption v sobe drzi inforrmace o tom kolik zarizeni bere vody, plynu a elektriny + kazde zarizeni ma jinou spotrebu podle toho v jakem je zrovna stavu (on/off/idle)
- trida ConsumptionData je kontejner na sber celkove spotreby zarizeni (s casem se zvetsuje pokud neni zarizeni vypnute)
 
___
F4. Jednotlivá zařízení mají API na sběr dat o tomto zařízení. O zařízeních sbíráme data jako spotřeba elektřiny, plynu, vody a funkčnost (klesá lineárně s časem)

- spotreba popsana v bodu F3 (trida ConsumptionData v Device)
- funkcnost je popsana v bodu F1 (promenna lifespan v Device)

___
F5. Jednotlivé osoby a zvířata mohou provádět aktivity(akce), které mají nějaký efekt na zařízení nebo jinou osobu. Např. Plynovy_kotel_1[oteverny_plyn] + Otec.zavritPlyn(plynovy_kotel_1) -> Plynovy_kotel_1[zavreny_plyn].

- zarizeni a vozidla (Device a Vehicle extendi Accessory) maji metody use() a stopUsing()
- metoda use() dela u kazdeho zarizeni vhodnou akci + u nekterych devices to zmeni stav pokud by to tak bylo i v realnem zivote
- metoda use() u vozidel (napr auto, kolo, snowboard) ho pouze pouziji a je to povazovano za venkovni aktivitu

___
F6. Jednotlivá zařízení a osoby se v každém okamžiku vyskytují v jedné místnosti (pokud nesportují) a náhodně generují eventy (eventem může být důležitá informace a nebo alert)

- osoby a zvirata se behem simulace nahodne pohybuji z pokoje do pokoje a v kazdem pokoji zvoli nejakou lumparnu kterou udelaji (ktery device pouzijou a na jak dlouho ho pouzijou)
- toto vzdy vygeneruje aktivitu ktera je nasledne zapsana do ActivityAndUsage reportu

___
F7. Eventy jsou přebírány a odbavovány vhodnou osobou (osobami) nebo zařízením (zařízeními).

- zarizeni se mohou rozbit, to vygeneruje event, ktery je zapsan do EventReportu a zaroven na toto reaguje osoba tim poverena. Detailneji je toto popsane v bodu F9.
- s nejakou sanci take muze nastat nejaky externi event (napr fouka vitr, vypadnou pojistky). Zarizeni vhodne reaguji na tyto eventy (napr vsechny zaluzie se vytahnou). Toto je obstarano senzory WindSensor, CircuitBreakerSensor

___
F8. Vygenerování reportů

- tridy HouseConfigReport, EventReport, ActivityAndUsageReport a ConsumptionReport
- HouseConfig report se vytvori po vybudovani domu
- EventReport, ActivityAndUsageReport a ConsumptionReport se vsechny vytvori po dobehnuti simulace

___
F9. Při rozbití zařízení musí obyvatel domu prozkoumat dokumentaci k zařízení - najít záruční list, projít manuál na opravu a provést nápravnou akcí (např. Oprava svépomocí, koupě nového atd.). Manuály zabírají mnoho místa a trvá dlouho než je najdete. Hint: Modelujte jako jednoduché akce ...dokumentace je přístupná jako proměnná přímo v zařízení, nicméně se dotahuje až, když je potřeba.

- pokud se zarizeni rozbije, tak je upozornen clovek ktery je k tomuto zarizeni pridan/pripojen/attachnut a pokusi se ho opravit
- Kazdy clen rodiny ma urcitou hranici co dokaze a co nedokaze opravit
- V pripade, ze to dana osoba dokaze opravit, tak to opravi, pokud to nedokaze, tak zavola nekoho jineho z rodiny
- Pokud to nedokaze opravit nikdo z rodiny, tak je zavolan opravar (handyman), ktery to opravi.

___
F10. Rodina je aktivní a volný čas tráví zhruba v poměru (50% používání spotřebičů v domě a 50% sport kdy používá sportovní náčiní kolo nebo lyže). Když není volné zařízení nebo sportovní náčiní, tak osoba čeká.

- osoby maji 50% sanci ze pouziji nejake vozidlo (auto kolo snowboard atd) coz je povazovano za venkovni aktivitu a 50% sanci ze pouziji nejake zarizeni. Pokud jsou vsechna zarizeni a vozidla obsazena, tak osoba ceka nez se neco uvolni.
- zvirata mohou pouzivat pouze zarizeni jim urcena (napr smartAnimalFeeder) a nemohou pouzivat vozidla.
</details>


## Pouzite design patterny
<details><summary>Click here to show "Pouzite design patterny"</summary>
<br/>

1. Strategy

- tridy ConsumptionStrategy, OnConsumptionStrategy, OffConsumptionStrategy a IdleConsumptionStrategy
- strategy jsme pouzili u sberu spotreby u zarizeni. Mame on/off/idle strategy, mezi kterymi jde prepinat a podle toho se nacita celkova spotreba zarizeni

---

2. Builder

- trida HouseBuilder
- pouzity pri stavbe domu

---

3. Observer


1) 

- interface DeviceObserver a DeviceObservableSubject
- trida Person implementuje DeviceObserver
- trida Device implementuje DeviceObservableSubject
- pouzity na reagovani na eventy vygenerovane zarizenimi -\> zarizeni se rozbije a nekdo ho dojde opravit

2) 

- interface SensorObserver a SensorObservableSubject
- trida DeviceController implementuje SensorObserver
- trida Sensor implementuje SensorObservableSubject
- pouzity na reagovani na eventy vygenerovane senzory -\> vypadnou pojistky a vsechna zarizeni se vypnou

---

4. Facade

- tridy Device a DeviceController
- pouzity jako APIcko pro device

---

5. Singleton

- tridy HouseConfigReport, ConsumptionReport, EventReport, ActivityAndUsageReport

---

6. ChainOfResponsibility

- interface RepairChainOfResponsibilityHandler
- trida Person implemetuje RepairChainOfResponsibilityHandler
- pouzito pri opravovani rozbitych zarizeni. Kdyz to nedokazu opravit ja, tak zavolam nekoho kdo ma vetsi opravovaci schopnost nez ja.

---

7. Stream (mate to v zadani napsane jako pattern)

- rozhazeno vsude mozne v kodu kde to bylo vhodne
</details>



