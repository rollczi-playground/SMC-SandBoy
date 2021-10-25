# SMC-SandBoy
A small plugin for replacing blocks with others. Often used in minecraft guild wars.
### API
`SMCSandBoy` - instance of plugin<br>
`ReplacerService` - manages and stores replacers<br>
`Replacer` - block replacement algorithm<br>
`ReplacerValidator` - checks if the algorithm can continue replacing<br>
`NextMove` - functional interface to get next location for replacing blocks<br>
#### Get instance of SMCSandBoy and ReplacerService:
```java
SMCSandBoy plugin = SMCSandBoy.getInstance();
ReplacerService service = plugin.getReplacerService();
```
#### You can implement replacers yourself:
```java
StandardReplacer replacer = new StandardReplacer(Material.LAVA, NextMove.DOWN, ReplacerValidator.AIR_ONLY);
ItemBuilder item = new ItemBuilder(Material.LAPIS_BLOCK).setName("&9Mega Lava");

service.registerReplacer("custom_lava", replacer, item);
```
#### if you prefer chain:
```java
service.configuration()
        .name("custom_water")
        .replacer(new StandardReplacer(Material.WATER, NextMove.UP, ReplacerValidator.AIR_ONLY))
        .item(new ItemBuilder(Material.LAPIS_BLOCK).setName("&9Mega Water"))
        .register();
```
#### or edit the current...
```java
service.getReplacerData("boyFarmer").peek(replacerData -> {
    Replacer replacer = replacerData.getReplacer();
    replacer.addValidator((nextLocation, nextType, round) -> round < 20);

    ItemBuilder item = replacerData.getItem();
    item.setType(Material.STONE);
    item.setName("&9Change Name");
    replacerData.setItem(item);
});
```
