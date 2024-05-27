#!/bin/bash

### GIT FILTER-REPO ###

## NÃO EXECUTE ESSE SCRIPT DIRETAMENTE
## Esse script foi feito para uso do
## script 'trybe-publisher' fornecido
## pela Trybe.

[[ $# == 1 ]] && \
[[ $1 == "trybe-security-parameter" ]] && \
git filter-repo \
    --path .trybe \
    --path .github \
    --path trybe.yml \
    --path README.md \
    --path trybe-filter-repo.sh \
    --path src/test/java/com/betrybe/agrix/evaluation/mock \
    --path src/test/java/com/betrybe/agrix/evaluation/util \
    --path src/test/java/com/betrybe/agrix/evaluation/CropManagementTest.java \
    --path src/test/java/com/betrybe/agrix/evaluation/DockerfileTest.java \
    --path src/test/java/com/betrybe/agrix/evaluation/FarmManagementTest.java \
    --invert-paths --force --quiet