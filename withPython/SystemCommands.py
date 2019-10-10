# https://stackoverflow.com/questions/7016249/compiling-java-from-python

import subprocess
def compile_java(java_file):
    '''compile java program, if output it failed'''
    PIPE = subprocess.PIPE
    cmd = 'javac ' + java_file
    proc = subprocess.Popen(cmd, shell=True, stdout=PIPE)
    out = proc.communicate()[0].decode()
    if out:
        return False #compile failed - got output
    else:
        return True

def run_java(java_class):
    '''run a compiled java program and caputre output, must be in the same dir as the file'''
    PIPE = subprocess.PIPE
    cmd = "java " + java_class
    proc = subprocess.Popen(cmd, shell=True, stdout=PIPE, stderr=PIPE)
    out, err = proc.communicate()
    if err:
        print(err.decode())
    return out.decode()

def run(cmd):
    '''run a system command'''
    proc = subprocess.Popen(cmd, shell=True)

def git_log():
    '''get the timestamp of the latest commit'''
    PIPE = subprocess.PIPE
    cmd = "git log -1 --format=%ci"
    p = subprocess.Popen(cmd,shell=True,stdout=PIPE,stderr=PIPE)
    out,err = p.communicate()
    if err:
        print(err.decode())
    return out.decode()
