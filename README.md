# Drop Modifier

---

플레이어가 죽었을 때 드랍할 아이템의 목록을 설정합니다.

`config.yml` 에 `included`와 `excluded`, 두 개의 키가 있습니다.
이 중 하나만 선택해 사용하셔야 합니다.

ex)
```yaml
included:
  - diamond
  - wooden_sword

#excluded:
```
=> :white_check_mark:
```yaml
included:
  - diamond
  - wooden_sword

excluded:
  - emerald
  - diamond_axe
```
=> :x:
```yaml
#included:

#excluded:
```
=> :x:

`included`는 해당 목록의 아이템들만 드랍하게 합니다.
`excluded`는 드랍되는 아이템들에서 목록의 아이템들을 제외합니다.

문제가 있으시면 [Issues](https://github.com/spacedvoid/DropModifier/issues)에 제보하시면 됩니다.