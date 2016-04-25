@echo off
copy %1 tempP.pep
java PrettyPrinter tempP.pep > %1
