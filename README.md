# README

This is a java version `top`.

# Usage

Get `jtop` from `https://bitbucket.org/hatterjiang/jtop/src`.

```
$ java -jar jtop.jar 
[ERROR] pid is not assigned.
Usage[b121209]:
java -jar jtop.jar [options] <pid> [<interval> [<count>]]
-OR-
java -cp jtop.jar jtop [options] <pid> [<interval> [<count>]]
    -size <B|K|M|G|H>             Size, case insensitive (default: B, H for human)
    -thread <N>                   Thread Top N (default: 5)
    -stack <N>                    Stacktrace Top N (default: 8)
    -excludes                     Excludes (string.contains)
    -includes                     Includes (string.contains, excludes than includes)
    --color                       Display color (default: off)
    --sortmem                     Sort by memory allocted (default: off)
    --summaryoff                  Do not display summary (default: off)
    --advanced                    Do display like 'top' (default: off)
```


![jtop.png](https://bitbucket.org/repo/E9aogx/images/19642114-jtop.png)