	.file	"GCDRF.c"
	.text
	.globl	gcd
	.type	gcd, @function
gcd:
.LFB0:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	subl	$12, %esp
	pushl	$64
	call	DumpS
	addl	$16, %esp
	cmpl	$0, 8(%ebp)
	jne	.L2
	movl	12(%ebp), %eax
	movl	%eax, -12(%ebp)
	jmp	.L3
.L2:
	movl	12(%ebp), %eax
	cltd
	idivl	8(%ebp)
	movl	%edx, %eax
	subl	$8, %esp
	pushl	8(%ebp)
	pushl	%eax
	call	gcd
	addl	$16, %esp
	movl	%eax, -12(%ebp)
.L3:
	subl	$12, %esp
	pushl	$64
	call	DumpS
	addl	$16, %esp
	movl	-12(%ebp), %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE0:
	.size	gcd, .-gcd
	.ident	"GCC: (GNU) 5.3.0"
	.section	.note.GNU-stack,"",@progbits
