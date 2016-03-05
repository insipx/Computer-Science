	.file	"FactorialRF.c"
	.text
	.globl	factorial
	.align	16, 0x90
	.type	factorial,@function
factorial:                              # @factorial
# BB#0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	movl	8(%ebp), %eax
	movl	$64, %ecx
	movl	%eax, -4(%ebp)
	movl	$64, (%esp)
	movl	%ecx, -12(%ebp)         # 4-byte Spill
	calll	DumpS
	cmpl	$1, -4(%ebp)
	jg	.LBB0_2
# BB#1:
	movl	$1, -8(%ebp)
	jmp	.LBB0_3
.LBB0_2:
	movl	-4(%ebp), %eax
	movl	-4(%ebp), %ecx
	subl	$1, %ecx
	movl	%ecx, (%esp)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	calll	factorial
	movl	-16(%ebp), %ecx         # 4-byte Reload
	imull	%eax, %ecx
	movl	%ecx, -8(%ebp)
.LBB0_3:
	movl	$64, %eax
	movl	$64, (%esp)
	movl	%eax, -20(%ebp)         # 4-byte Spill
	calll	DumpS
	movl	-8(%ebp), %eax
	addl	$24, %esp
	popl	%ebp
	ret
.Ltmp0:
	.size	factorial, .Ltmp0-factorial


	.ident	"FreeBSD clang version 3.4.1 (tags/RELEASE_34/dot1-final 208032) 20140512"
	.section	".note.GNU-stack","",@progbits
