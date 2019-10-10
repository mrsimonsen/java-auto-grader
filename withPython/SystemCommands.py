# https://stackoverflow.com/questions/7016249/compiling-java-from-python

import subprocess
def compile_java(java_file):
    '''compile java program, if output it failed'''
    cmd = 'javac ' + java_file
    proc = subprocess.Popen(cmd, shell=True)
    if out:
        return False #compile failed - got output
    else:
        return True

def run_java(java_class):
    '''run a compiled java program and caputre output'''
    cmd = "java " + java_class

    proc = subprocess.Popen(cmd, shell=True)
    return out

def run(cmd):
    '''run a system command'''
    proc = subprocess.Popen(cmd, shell=True)
