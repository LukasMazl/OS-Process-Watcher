# OS Process Watcher

Sorry but this repository is only for czech students. :)

Repozitář slouží k procvičování práce s procesy, které jsou postupně za sebou spuštěny. Úkolem je danné procesy ,,zabít", protože se jedná o nebezpečné viry! (jen jako). Aplikaci je možné spustit pomocí přiloženého skriptu start.sh, viz. příklad níže.

```console
foo@bar:~$ sh ./start.sh
```

Skript vytvoří klone repozitáře a zahájí fázi sestavení (build) aplikace. Po sestavení je aplikace automaticky spuštěna.

### Systémové nároky?
Aplikace potřebuje:
  - Java 8 (ne výšší)
  - Linux s nastavenou systémovou proměnou JAVA_HOME (kvůli mvnw)

## Jak začít?
Po spuštění příkazu výše, pokračujte podle informací, které jsou vypsány na obrazovce. Pro zabití procesu je potřeba mít zapnutý nový terminál a v něm pracovat. Pokud nebezpečný vir zneškodníte, tak to aplikace sama zaznamená a posune vás dále v ději.

## Co je cílem aplikace?
Cílem aplikace je zneškodnit ,,nebezpečné" viry a naučit studenty pracovat s nástroji pro správu a kontrolování procesů v operačním systému. Cílem studenta je zneškodnit nebezpečné viry příkazem kill PID a nashromáždit přitom co nejvíce možný způsobů (příkazů v terminálu), pomocí kterých lze procesy sledovat.

## Prosba
Pokud Vás napadne další úkol nebo máte návrh na zlepšení, tak jen do toho. Založte Issue, budu moc rád za každou zpětnou vazbu :)
