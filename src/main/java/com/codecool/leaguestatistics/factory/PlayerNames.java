package com.codecool.leaguestatistics.factory;

public class PlayerNames {
    private static String[] PlayerNames;
    private static String[] CityNames;
    private static String[] TeamNames;
    
    
    static {
        PlayerNames = new String[] {"Roger Baker",
"Justin Sanchez",
"Juan Mitchell",
"Aaron Brooks",
"George Scott",
"Willie Perez",
"Raymond Miller",
"Peter Anderson",
"Howard Edwards",
"Carlos Garcia",
"David Phillips",
"Jonathan Ward",
"Kenneth Brown",
"Shawn Lee",
"Brian James",
"Martin Patterson",
"Joe Foster",
"Arthur Parker",
"Gerald Price",
"Randy Gonzales",
"Charles Gonzalez",
"Jose Jenkins",
"Jason Simmons",
"Chris Thompson",
"Scott Cox",
"Joshua Roberts",
"Gary Johnson",
"Robert Diaz",
"Thomas Jones",
"Albert Rogers",
"Eric Morris",
"Jerry Butler",
"Phillip Peterson",
"Ernest Hall",
"Jeffrey Hernandez",
"Ronald Torres",
"Earl Bailey",
"Lawrence Thomas",
"Anthony Allen",
"Larry Coleman",
"Wayne Collins",
"Johnny Williams",
"Terry Bell",
"Mark Wood",
"Craig Russell",
"Henry Hughes",
"Jack Bryant",
"Bobby White",
"Samuel Watson",
"Michael Robinson",
"Edward Jackson",
"Jesse Gray",
"Victor Reed",
"Walter Henderson",
"Paul Rivera",
"Richard Smith",
"James Stewart",
"Roy Clark",
"Steve Lewis",
"Joseph Adams",
"Matthew Sanders",
"Harold Campbell",
"Timothy Evans",
"Philip Rodriguez",
"Sean Carter",
"Donald Taylor",
"Adam Lopez",
"Gregory Murphy",
"Frank Ramirez",
"Ralph Washington",
"Alan Walker",
"Carl Howard",
"Todd Long",
"Christopher Alexander",
"Jimmy Kelly",
"Fred Green",
"William Bennett",
"Daniel Richardson",
"Harry Ross",
"Stephen Morgan",
"Steven Flores",
"Clarence Barnes",
"Keith Wilson",
"Douglas Moore",
"Andrew Hill",
"Brandon Nelson",
"Eugene Harris",
"Kevin Young",
"Dennis Cook",
"Benjamin Powell",
"Ryan Perry",
"Bruce Davis",
"Louis Griffin",
"Patrick Cooper",
"Nicholas Wright",
"Jeremy Turner",
"Russell Martin",
"Billy Martinez",
"John King",
"Alonzo Hayes",
"Alan Bowers",
"Bradford Harper",
"Victor Barrett",
"Terrence Harvey",
"Travis Carson",
"Maurice Massey",
"Marco Carter",
"Wilbur Nunez",
"Garrett Ferguson",
"Moses Parks",
"Terrance Francis",
"Pablo Howard",
"Alex Matthews",
"Clifton Boyd",
"Ross Burton",
"Roman George",
"Rudy Pena",
"Gilberto Lynch",
"Isaac Young",
"Phil Phillips",
"Greg Vega",
"Joe Cruz",
"Roy Cook",
"Jessie Hodges",
"Derek Schmidt",
"Jason Sutton",
"Erik Miles",
"Ricky Rodriguez",
"Ronald Brown",
"Brendan Gregory",
"Bobby Caldwell",
"Dustin Zimmerman",
"Cameron May",
"Ian Jones",
"Duane Mills",
"Jackie Strickland",
"Otis Murray",
"Walter Powell",
"James Palmer",
"Enrique Reynolds",
"Kent Moreno",
"Raymond Mack",
"Leonard Bush",
"Craig Davis",
"Benjamin Morgan",
"Edward Washington",
"Keith Barnes",
"Ernest Alexander",
"Shawn Powell",
"Bruce Young",
"James Williams",
"Thomas Morris",
"Arthur Hall",
"Fred Watson",
"Peter Nelson",
"Frank Kelly",
"Carlos Evans",
"Larry Butler",
"Jeremy Simmons",
"Jason Allen",
"Jeffrey Howard",
"Victor Ramirez",
"Martin Lee",
"Gary Rodriguez",
"Lawrence Thomas",
"Henry Scott",
"Billy Taylor",
"Roger Price",
"Albert Bailey",
"Douglas Wilson",
"Jose Hill",
"Michael Moore",
"Jimmy Wright",
"Roy Peterson",
"Joseph Cook",
"Ralph Stewart",
"Raymond Coleman",
"Dennis King",
"Wayne Reed",
"Ryan Hernandez",
"Kenneth Smith",
"Willie Diaz",
"Eugene Rogers",
"John Parker",
"Bobby Cooper",
"William James",
"Anthony Bennett",
"Robert Lewandowski",
"Wojciech Szczesny",
"Piotr Zielinski",
"Kamil Glik",
"Arkadiusz Milik",
"Lukasz Piszczek",
"Karol Linetty",
"Bartosz Kapustka",
"Lukasz Skorupski",
"Bartosz Salamon",
"Pawel Olkowski",
"Bartosz Bereszynski",
"Artur Sobiech",
"Jakub Blaszczykowski",
"Eugen Polanski",
"Marcin Kaminski",
"Thiago Cionek",
"Rafal Gikiewicz",
"Mariusz Stepinski",
"Waldemar Sobota",
"Tomasz Kupisz",
"Kacper Przybylko",
"Artur Boruc",
"Rafal Wolski",
"Bartlomiej Dragowski",
"Jacek Goralski",
"Adam Matuszczyk",
"Michal Pazdan",
"Radoslaw Murawski",
"Artur Jedrzejczyk",
"Jaroslaw Niezgoda",
"Filip Starzynski",
"Krzysztof Maczynski",
"Szymon Zurkowski",
"Damian Dabrowski",
"Maciej Sadlok",
"Mateusz Matras",
"Pawel Bochniewicz",
"Piotr Leciejewski",
"Adam Stachowiak",
"Dawid Kownacki",
"Igor Lasicki",
"Przemyslaw Szyminski",
"Dominik Furman",
"Michal Kucharczyk",
"Adam Fraczczak",
"Jakub Kosecki",
"Oskar Zawada",
"Patryk Malecki",
"Przemyslaw Frankowski",
"Patryk Lipski",
"Daniel Lukasik",
"Michal Chrapek",
"Ariel Borysiuk",
"Jaroslaw Fojut",
"Blazej Augustyn",
"Pawel Stolarski",
"Rafal Kurzawa",
"Slawomir Peszko",
"Grzegorz Kuswik",
"Tomasz Jodlowiec",
"Lukasz Szukala",
"Damian Zbozien",
"Maciej Dabrowski",
"Konrad Michalak",
"Krzysztof Piatek",
"Karol Swiderski",
"Dawid Kort",
"Mateusz Mak",
"Karol Mackiewicz",
"Arkadiusz Reca",
"Damian Kadzior",
"Mateusz Cetnarski",
"Kamil Mazek",
"Szymon Drewniak",
"Kamil Drygas",
"Alan Czerwinski",
"Jakub Wojcicki",
"Patryk Dziczek",
"Mateusz Mozdzen",
"Damian Szymanski",
"Michal Kopczynski",
"Jakub Piotrowski",
"Bartosz Szeliga",
"Jakub Zubrowski",
"Pawel Jaroszynski",
"Piotr Malarczyk",
"Adam Danch",
"Mateusz Wieteska",
"Michal Nalepa",
"Sebastian Rudol",
"Michal Helik",
"Jakub Czerwinski",
"Adam Dzwigala",
"Bartosz Kopacz",
"Jacek Kielb",
"Grzegorz Piesio",
"Bartosz Rymaniak",
"Martin Konczkowski",
"Kamil Sylwestrzak",
"Kamil Dankowski",
"Lukasz Burliga",
"Piotr Celeban",
"Michal Marcjanik",
"Tomasz Loska",
"Jakub Wrabel",
"Bartosz Wolski",
"Mateusz Machaj",
"Adam Kokoszka",
"Michal Mak",
"Michal Buchalik",
"Arkadiusz Piech",
"Sebastian Tyrala",
"Lukasz Broz",
"Jakub Lukowski",
"Kamil Wojtkowski",
"Sebastian Szymanski",
"Patryk Tuszynski",
"Bartlomiej Pawlowski",
"Marcin Listkowski",
"Lukasz Zwolinski",
"Bartosz Spiaczka",
"Lukasz Wolsztynski",
"Pawel Dawidowicz",
"Tomasz Holota",
"Piotr Wlazlo",
"Damian Rasak",
"Michal Gardawski",
"Mateusz Kupczak",
"Szymon Matuszek",
"Filip Jagiello",
"Bartosz Kwiecien",
"Alan Uryga",
"Marcus Piossek",
"Martin Kobylanski",
"Lukasz Sekulski",
"Kamil Bilinski",
"Dominik Kun",
"Maciej Jankowski",
"Mateusz Szwoch",
"Karol Angielski",
"Mikolaj Kwietniewski",
"Lukasz Janoszka",
"Jonatan Straus",
"Daniel Dziwniel",
"Kamil Slaby",
"Jakub Bartosz",
"Mateusz Lewandowski",
"Jakub Tosik",
"Jaroslaw Kubicki",
"Michal Nalepa",
"Damian Byrtek",
"Lukasz Moneta",
"Mateusz Szczepaniak",
"Marcin Cebula",
"Przemyslaw Mystkowski",
"Adam Buksa",
"Aleksander Jagiello",
"Jakub Arak",
"Maciej Gorski",
"Radoslaw Cierzniak",
"Lukasz Sierpina",
"Michal Gliwa",
"Dariusz Trela",
"Marcin Pietrowski",
"Patryk Stepinski",
"Adam Marciniak",
"Tomasz Cywka",
"Dawid Szymonowicz",
"Tadeusz Socha",
"Maciej Urbanczyk",
"Grzegorz Wojtkowiak",
"Bartlomiej Babiarz",
"Przemyslaw Szarek",
"Michal Koj",
"Krzysztof Janus",
"Mikolaj Lebedynski",
"Milosz Przybecki",
"Arkadiusz Wozniak",
"Marcin Warcholak",
"Radoslaw Janukiewicz",
"Kornel Osyra",
"Rafal Siemaszko",
"Seweryn Kielpin",
"Grzegorz Sandomierski",
"Mateusz Cholewiak",
"Sebastian Steblecki",
"Maciej Gostomski",
"Mateusz Zachara",
"Rafal Grzelak",
"David Niepsuj",
"Adam Wolniewicz",
"Adrian Basta",
"Rafal Pietrzak",
"Lukasz Tyminski",
"Bartlomiej Kasprzak",
"Lukasz Piatek",
"Patryk Fryc",
"Grzegorz Bonin",
"Mariusz Pawelec",
"Adam Mojta",
"Damian Piotrowski",
"Marek Wasiluk",
"Rafal Boguski",
"Jakub Wawrzyniak",
"Piotr Cwielong",
"Lukasz Wronski",
"Grzegorz Goncerz",
"Krystian Wojcik",
"Jakub Slowik",
"Michal Janota",
"Mateusz Kuchta",
"Mateusz Kryczka",
"Krzysztof Baran",
"Jakub Bartkowski",
"Radoslaw Pruchnik",
"Pawel Oleksy",
"Marcin Urynowicz",
"Hubert Adamczyk",
"Mateusz Wdowiak",
"Denis Gojko",
"Rafal Wolsztynski",
"Filip Piszczek",
"Rafal Figiel",
"Piotr Marciniec",
"Lukasz Zaluska",
"Adam Deja",
"Sebastian Bonecki",
"Dawid Blanik",
"Cezary Stefanczyk",
"Marcin Biernat",
"Damian Podlesny",
"Michal Jakobowski",
"Konrad Jalocha",
"Maciej Domanski",
"Adrian Blad",
"Kamil Kurowski",
"Patryk Kun",
"Rafal Augustyniak",
"Bartosz Jaroch",
"Krystian Getinger",
"Rafal Brusilo",
"Wojciech Trochim",
"Maciej Korzym",
"Bartlomiej Dudzic",
"Krzysztof Drzazga",
"Maksymilian Hebel",
"Krzysztof Danielewicz",
"Lukasz Kosakiewicz",
"Kamil Pestka",
"Hubert Matynia",
"Jakub Kuzdra",
"Bartosz Nowak",
"Rafal Makowski",
"Seweryn Michalski",
"Damian Chmiel",
"Michal Czarny",
"Mateusz Zyro",
"André Dej",
"Tomasz Nowak",
"Jakub Szumski",
"Dawid Kudla",
"Mateusz Abramowicz",
"Rafal Leszczynski",
"Marcin Staniszewski",
"Lukasz Hanzel",
"Bartosz Zurek",
"Adam Wilk",
"Kamil Zapolnik",
"Tomasz Zajac",
"Lukasz Budzilek",
"Michal Fidziukiewicz",
"Cezary Demianiuk",
"Marcin Flis",
"Hubert Wolakiewicz",
"Pawel Brozek"};

        TeamNames = new String[] {
"Pogoń Szczury",
"Raków",
"Lech Poznań",
"Lechia",
"Radomiak",
"Piast Rządzi",
"Górnik Wytrwały",
"Wisła Szeroka",
"Cracovia",
"Legia Czerwona Gwiazda",
"Stal",
"Jagiellonia",
"Warta Wartka",
"Śląsk Wrocław",
"Zagłębie Ruhry",
"Wisła Wartka",
"BrukBet",
"Górnik Na Rencie",
"Miedź",
"Widzew",
"Arka",
"Korona",
"Chrobry",
"ŁKS Podbipięta",
"GKS Gleboki",
"Sandecja",
"Odra Zamulona",
"Podbeskidzie",
"Resovia",
"GKS Wykopek",
"Skra",
"Zagłębie Głebokie",
"Puszcza",
"Górnik",
"Stomil",
"GKS Jastrzębie",
"Stal Nierdzewna",
"Chojniczanka",
"Ruch",
"Motor",
"Lech II Poznań",
"Wigry",
"Olimpia",
"Garbarnia",
"Radunia",
"Znicz",
"KKS Pociąg",
"Wisła Zamulona",
"Śląsk II Wrocław",
"Pogoń Kacapa",
"Hutnik",
"Pogoń Ruska",
"Sokół",
"GKS",
"Cracovia",
"ŁKS Łagów",
"Siarka",
"Podhale",
"Chełmianka",
"Stal",
"Unia",
"Avia",
"Wisłoka",
"Orlęta",
"Podlasie",
"KSZO",
"Czarni",
"Korona Szczyt",
"Wisła Płynąca",
"Sokół",
"Tomasovia",
"Wólczanka",
"Zagłębie II Lubin",
"Ślęza",
"Polonia Jeden",
"LKS Goczałkowice",
"Miedź II Legnica",
"Rekord",
"Górnik II Zabrze",
"Gwarek Tarnowski",
"Odra Wodzisław",
"Carina",
"Pniówek",
"Stal Brzeg",
"Piast Żmigród",
"Warta Gorzów Wielkopolski",
"Lechia Zielona Góra",
"MKS Kluczbork",
"Karkonosze",
"FotoHigiena Gać"};

        CityNames = new String[] {
"Warszawa",
"Kraków",
"Łódź",
"Wrocław",
"Poznań",
"Gdańsk",
"Szczecin",
"Bydgoszcz",
"Lublin",
"Białystok",
"Katowice",
"Gdynia",
"Częstochowa",
"Radom",
"Toruń",
"Sosnowiec",
"Kielce",
"Rzeszów",
"Gliwice",
"Zabrze",
"Olsztyn",
"Bielsko-Biała",
"Bytom",
"Zielona Góra",
"Rybnik",
"Ruda Śląska",
"Opole",
"Tychy",
"Gorzów Wielkopolski",
"Dąbrowa Górnicza",
"Elbląg",
"Płock",
"Wałbrzych",
"Włocławek",
"Tarnów",
"Chorzów",
"Koszalin",
"Kalisz",
"Legnica",
"Grudziądz",
"Jaworzno",
"Słupsk",
"Jastrzębie-Zdrój",
"Nowy Sącz",
"Jelenia Góra",
"Siedlce",
"Mysłowice",
"Konin",
"Piotrków Trybunalski",
"Piła",
"Inowrocław",
"Lubin",
"Ostrów Wielkopolski",
"Suwałki",
"Ostrowiec Świętokrzyski",
"Gniezno",
"Stargard",
"Głogów",
"Siemianowice Śląskie",
"Pabianice",
"Leszno",
"Zamość",
"Łomża",
"Chełm",
"Tomaszów Mazowiecki",
"Żory",
"Ełk",
"Pruszków",
"Tarnowskie Góry",
"Przemyśl",
"Stalowa Wola",
"Kędzierzyn-Koźle",
"Mielec",
"Tczew",
"Bełchatów",
"Biała Podlaska",
"Świdnica",
"Będzin",
"Zgierz",
"Piekary Śląskie",
"Racibórz",
"Legionowo",
"Ostrołęka",
"Świętochłowice",
"Wejherowo",
"Zawiercie",
"Rumia",
"Starachowice",
"Skierniewice",
"Wodzisław Śląski",
"Piaseczno",
"Starogard Gdański",
"Puławy",
"Tarnobrzeg",
"Krosno",
"Kołobrzeg",
"Radomsko",
"Dębica",
"Skarżysko-Kamienna",
"Otwock"};
    }

    public static String[] getPlayerNames() {
        return PlayerNames;
    }

    public static String[] getCityNames() {
        return CityNames;
    }

    public static String[] getTeamNames() {
        return TeamNames;
    }
}
